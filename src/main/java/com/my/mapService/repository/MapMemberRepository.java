package com.my.mapService.repository;

import com.my.mapService.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//@Repository
public class MapMemberRepository implements MemberRepository {
    //전체멤버를 저장할 멥을 선언
    public static Map<Long, MemberDto> store = new HashMap<>();
    //map에 저장할때 사용할 ID(키값)선언
    //밑에 친구는 초기값을 가지고 있어야하며 L을 붙여야한다
    private static Long sequnce=1L;
    @Override
    public MemberDto save(MemberDto memberDto) {
        memberDto.setId(sequnce++);
//        sequnce++;
        store.put(memberDto.getId(), memberDto);
        return memberDto;
    }
//    Optional 로성고하면 찾아서 주고 없으면 null값을 줄꺼야
    @Override
    public Optional<MemberDto> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<MemberDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<MemberDto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public MemberDto updateById(Long memberId, MemberDto memberDto) {
        return null;
    }

    public void clearStore(){
        store.clear();
        sequnce = 1L;
    }
}
