package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.models.entity.User;
import com.example.demo.models.enumclass.UserStatus;
import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.UserApiRequest;
import com.example.demo.models.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {
    //@Autowired
    //private UserRepository userRepository;

    // 요청 데이터를 읽고, 유저를 생성하여 UserApiResponse를 만들어 반환
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status(UserStatus.REGISTERED)
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .email(userApiRequest.getEmail())
                        .registeredAt(LocalDateTime.now())
                        .build();
        User newUser = baseRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        Optional<User> optional = baseRepository.findById(id);

        return  optional.map(user -> response(user)) // user가 있다면. map은 데이터 형태를 변경하기 위함
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
    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                                                            .id(user.getId())
                                                            .account(user.getAccount())
                                                            .password(user.getPassword())
                                                            .email(user.getEmail())
                                                            .phoneNumber(user.getPhoneNumber())
                                                            .status(UserStatus.REGISTERED)
                                                            .registeredAt(user.getRegisteredAt())
                                                            .unregisteredAt(user.getUnregisteredAt())
                                                            .build();
        return Header.OK(userApiResponse);
    }
}