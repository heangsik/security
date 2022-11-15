package kr.co.yhs.security.service;

import kr.co.yhs.security.entity.Member;
import kr.co.yhs.security.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    MemberRepository repository;

    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> member = repository.findByUserName(username);

        if (member.isEmpty()) {
            throw new UsernameNotFoundException("not found user");
        }
        return User.builder()
                .username(member.get().getUserName())
                .password(member.get().getPasswrod())
                .roles(member.get().getRole())
                .build();
    }

    public Member createMember(Member member) {
        member.setPasswordEncod(encoder);
        Member rtnObj = repository.save(member);
        log.info("================================"+rtnObj);
        return rtnObj;
    }
}
