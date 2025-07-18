package com.sparta.memo;

import com.sparta.memo.week2.SignupRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SignupRequestDtoTests {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void test1(){ // 성공하는 경우
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        String email="dds@naver.com";
        signupRequestDto.setName("name");
        signupRequestDto.setPassword("password");
        signupRequestDto.setEmail(email);

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        assertEquals(0, violations.size());
    }

    @Test
    void test2(){ //실패하는 경우
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        String email="dds@m@@sn.c--om";
        signupRequestDto.setName("name");
        signupRequestDto.setPassword("password");
        signupRequestDto.setEmail(email);

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        assertEquals(1, violations.size());
        assertEquals("잘못된 이메일 형식입니다.", violations.iterator().next().getMessage());
    }



}
