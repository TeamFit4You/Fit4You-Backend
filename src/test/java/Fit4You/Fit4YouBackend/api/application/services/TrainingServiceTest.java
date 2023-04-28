package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class TrainingServiceTest {

    @Mock
    TrainingPort trainingPort;
    @Mock
    WorkoutPort workoutPort;
    @Mock
    ExercisePort exercisePort;
    @Mock
    LoadMemberPort loadMemberPort;

    TrainingService trainingService;

    @BeforeEach
    void beforeEach() {
        trainingService= new TrainingService(trainingPort,workoutPort,exercisePort,loadMemberPort);
    }

    @Test
    @DisplayName("추천운동목록 생성:성공")
    void create() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member = Member.builder()
                .email(request.getEmail())
                .password(PasswordEncoder.encode(request.getPassword()))
                .build();
        List<MedicalHist> medicalHists = member.getMedicalHists();
        List<Condition> conditions = member.getConditions();
        //condition,hist추가

        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);

        //when

        //then
    }
}