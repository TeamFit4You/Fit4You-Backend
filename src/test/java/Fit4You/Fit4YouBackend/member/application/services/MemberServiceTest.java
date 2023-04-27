package Fit4You.Fit4YouBackend.member.application.services;

import Fit4You.Fit4YouBackend.exception.InvalidSignInInfo;
import Fit4You.Fit4YouBackend.member.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.member.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.member.domains.Member;
import Fit4You.Fit4YouBackend.member.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.member.dto.request.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    RegisterMemberPort registerMemberPort;
    @Mock
    LoadMemberPort loadMemberPort;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberService= new MemberService(registerMemberPort, loadMemberPort);
    }


    @Test
    @DisplayName("회원가입:성공")
    void signUp() {
        //given
        SignUpRequest request = SignUpRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        List<Member> members = new ArrayList<>();
        given(registerMemberPort.registerMember(any(Member.class)))
                .will(invocation->{
                    members.add(invocation.getArgument(0));
                    return 1L;
                });
        //when
        memberService.signUp(request);
        Member member = members.get(0);

        //then
        assertThat(member.getEmail()).isEqualTo(request.getEmail());
        assertThat(PasswordEncoder.matches(request.getPassword(), member.getPassword())).isTrue();
    }

    @Test
    @DisplayName("회원가입:실패")
    void signUp_1() {
        //given
        SignUpRequest request = SignUpRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        List<Member> members = new ArrayList<>();
        given(registerMemberPort.registerMember(any(Member.class)))
                .will(invocation->{
                    members.add(invocation.getArgument(0));
                    return 1L;
                });
        //when
        memberService.signUp(request);
        Member member = members.get(0);

        //then
        assertThat(member.getEmail()).isEqualTo(request.getEmail());
        assertThat(PasswordEncoder.matches(request.getPassword(), member.getPassword())).isTrue();
    }

    @Test
    @DisplayName("로그인:성공")
    void signIn() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member = Member.builder()
                .email(request.getEmail())
                .password(PasswordEncoder.encode(request.getPassword()))
                .build();

        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);

        //expected
        assertThat(memberService.signIn(request)).isNull();
    }

    @Test
    @DisplayName("로그인:실패 - 로그인정보 불일치")
    void signIn_1() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member = Member.builder()
                .email(request.getEmail())
                .password("wrongPw")
                .build();

        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);

        //expected
        assertThatThrownBy(() -> memberService.signIn(request))
                .isInstanceOf(InvalidSignInInfo.class);
    }
}