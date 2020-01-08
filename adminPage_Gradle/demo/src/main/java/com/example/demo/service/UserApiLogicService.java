package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.models.entity.OrderGroup;
import com.example.demo.models.entity.User;
import com.example.demo.models.enumclass.Status;
import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.UserApiRequest;
import com.example.demo.models.network.response.OrderGroupApiResponse;
import com.example.demo.models.network.response.UserApiResponse;
import com.example.demo.models.network.response.ItemApiResponse;
import com.example.demo.models.network.response.UserOrderInfoApiResponse;
import com.example.demo.service.ItemApiLogicService;
import com.example.demo.service.OrderGroupApiLogicService;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {
    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;
    //@Autowired
    //private UserRepository userRepository;

    // 요청 데이터를 읽고, 유저를 생성하여 UserApiResponse를 만들어 반환
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status(Status.REGISTERED)
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .email(userApiRequest.getEmail())
                        .registeredAt(LocalDateTime.now())
                        .build();
        User newUser = baseRepository.save(user);

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        Optional<User> optional = baseRepository.findById(id);

        return  optional.map(user -> response(user)) // user가 있다면. map은 데이터 형태를 변경하기 위함
                        .map(Header::OK)
                        .orElseGet(() -> Header.ERROR("데이터가 없음")); //user가 없다면
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        // 요청으로 들어온 아이디를 기준으로 유저 객체를 찾음
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());
        
        return optional.map(user -> {
            user.setAccount(userApiRequest.getAccount())
                .setPassword(userApiRequest.getPassword())
                .setStatus(userApiRequest.getStatus()) // 다만 이상한 값이 들어오면 에러 발생
                .setPhoneNumber(userApiRequest.getPhoneNumber())
                .setRegisteredAt(userApiRequest.getRegisteredAt())
                .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
            })
            .map(user -> baseRepository.save(user)) // update
            .map(updateUser -> response(updateUser)) // make apiResponse
            .map(Header::OK)
            .orElseGet(() -> Header.ERROR("데이터가 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = baseRepository.findById(id);
       
        return optional.map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터가 없음"));
    }   
    
    // User > userApiresponse
    private UserApiResponse response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                                                            .id(user.getId())
                                                            .account(user.getAccount())
                                                            .password(user.getPassword())
                                                            .email(user.getEmail())
                                                            .phoneNumber(user.getPhoneNumber())
                                                            .status(Status.REGISTERED)
                                                            .registeredAt(user.getRegisteredAt())
                                                            .unregisteredAt(user.getUnregisteredAt())
                                                            .build();
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponsesList = users.stream()
                                                        .map(user -> response(user))
                                                        .collect(Collectors.toList());
        
        return Header.OK(userApiResponsesList);
	}

    // 주문에 대한 상세정보 조회. 한번에 내려줄때도 있고 아이디로 연쇄적으로 검색해서 할 수도 있고
	public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
            .map(orderGroup -> {
                OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
                
                List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                    .map(detail -> detail.getItem())
                    .map(item -> itemApiLogicService.response(item).getData())
                    .collect(Collectors.toList());

                orderGroupApiResponse.setItemApiResponse(itemApiResponseList);
                return orderGroupApiResponse;
            }).collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
            .userApiResponse(userApiResponse)
            .build();

        return Header.OK(userOrderInfoApiResponse);
	}
}