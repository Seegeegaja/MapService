package com.my.mapService.service;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.repository.MapMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor // 조건별 생성자 생성
public class MemberService {
//    1.Field 주입 방법
//    @Autowired
//    MapMemberRepository memberRepository;
    //2.필드(Getter,setter) 주입방법
    //@RequiredArgsConstructor
//    private final MapMemberRepository repository;

    //3 생성자 주입 방법
    private final MapMemberRepository memberRepository;

    public MemberService(MapMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원 가입기능
    public Long join(MemberDto memberDto) {
        //같은 이름이 있는 중복 회원은 XXXX
        Optional<MemberDto> result = memberRepository
                .findByName(memberDto.getName());
        Optional<MemberDto> sum = memberRepository.findByName(memberDto.getAddress());

        if (result.isPresent()) {

            //true가 걸리면 같은이름이 존재
            return -1L;
        } else{
            if (sum.isPresent()) {
                return -1L;
            } else {
                MemberDto save = memberRepository.save(memberDto);
                return save.getId();
            }
            //입력 가능
//            MemberDto save = memberRepository.save(memberDto);
//            return save.getId();
        }
    }
    //아이디로 검색하서 한개 찾기
    public Optional<MemberDto> findOne(Long id) {
        return memberRepository.findById(id);
    }

}
