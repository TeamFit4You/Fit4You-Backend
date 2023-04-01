package Fit4You.Fit4YouBackend.member.application.services;

import Fit4You.Fit4YouBackend.member.application.ports.out.LoadMemberPort;
import Fit4You.Fit4YouBackend.member.application.ports.out.RegisterMemberPort;
import Fit4You.Fit4YouBackend.member.domains.Member;
import Fit4You.Fit4YouBackend.member.dto.request.SignInRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    RegisterMemberPort registerMemberPort;
    @Mock
    LoadMemberPort loadMemberPort;

    MemberService memberService = new MemberService(registerMemberPort,loadMemberPort);

//    @Test
//    void signUp() {
//        //given
//        SignUpRequest request = SignUpRequest.builder()
//                .email("test@email.com")
//                .password("testPw")
//                .build();
//        Member member1 = Member.builder()
//                .email("test@email.com")
//                .password("testPw")
//                .build();
//        List<Member> members = new ArrayList<>();
//        when(registerMemberPort.registerMember(any())).thenReturn(members.add(member1));//
//
//        //when
//        memberService.signUp(request);
//
//        //then
//
//    }

    @Test
    void signIn() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member1 = Member.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        //when
        when(loadMemberPort.loadMember(any())).thenReturn(member1);

        //then
        Assertions.assertThatThrownBy(() -> memberService.signIn(request))
                .isInstanceOf(RuntimeException.class);
    }
}