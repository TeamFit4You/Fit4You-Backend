package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.exception.MemberNotFound;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MemberPersistenceAdapterTest {

    @Autowired
    private MemberPersistenceAdapter memberPersistenceAdapter;

    @Autowired
    private MemberJpaRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원등록")
    void registerMember() {
        //given
        Member member = Member.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        //when
        Long savedId = memberPersistenceAdapter.registerMember(member);
        Member findMember = memberRepository.findById(savedId).orElse(null);

        //then
        assertThat(memberRepository.count()).isEqualTo(1L);
        assertThat(findMember).isNotNull();
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());

    }

    @Test
    @DisplayName("이메일로 회원 찾기:성공")
    void loadMember() {
        //given
        Member member = Member.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        memberRepository.save(member);

        //when
        Member findMember = memberPersistenceAdapter.loadMember(member.getEmail());

        //then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());

    }

    @Test
    @DisplayName("이메일로 회원 찾기:실패 - 존재하지 않는 회원")
    void loadMember_1() {
        //given
        Member member = Member.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        memberRepository.save(member);

        //expected
        assertThatThrownBy(() -> memberPersistenceAdapter.loadMember("wrong@email.com"))
                .isInstanceOf(MemberNotFound.class);

    }
}