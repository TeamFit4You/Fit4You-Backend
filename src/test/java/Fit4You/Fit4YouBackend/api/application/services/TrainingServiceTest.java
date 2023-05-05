package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
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
    List<Exercise> exercises;

    @BeforeEach
    void beforeEach() {
        trainingService= new TrainingService(trainingPort,workoutPort,exercisePort,loadMemberPort);
        String[] parts = {"shoulder", "elbow", "ankle", "wrist", "lumbar", "neck"};
        exercises = new ArrayList<>();
        for (String part : parts) {
            exercises.add(Exercise.builder()
                    .disease(Disease.builder()
                            .name(part + "일반")
                            .relatedPart(part)
                            .build())
                    .name(part + "운동")
                    .setEa(3)
                    .build()
            );
        }
    }
    @Test
    @DisplayName("운동추천")
    void RecommendWorkout() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member = Member.builder()
                .email(request.getEmail())
                .password(PasswordEncoder.encode(request.getPassword()))
                .build();

        //condition
        Condition condition = Condition.builder()
                .member(member)
                .ankle(1f)
                .elbow(2f)
                .wrist(20f)
                .lumbar(4f)
                .neck(5f)
                .shoulder(6f)
                .build();
        member.getConditions().add(condition);
        //TODO hist추가

        given(exercisePort.getAll()).willReturn(exercises);

        //when
        List<Exercise> results = trainingService.getExercisesByPriority(member);

        //then
        assertThat(results.get(0).getName()).isEqualTo("wrist운동");
        assertThat(results.get(1).getName()).isEqualTo("shoulder운동");
        assertThat(results.get(2).getName()).isEqualTo("neck운동");
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

        //condition
        Condition condition = Condition.builder()
                .member(member)
                .ankle(1f)
                .elbow(2f)
                .wrist(20f)
                .lumbar(4f)
                .neck(5f)
                .shoulder(6f)
                .build();
        member.getConditions().add(condition);
        //TODO hist추가

        //stubbing
        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);
        given(trainingPort.create(any())).willReturn(1L);
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        TrainingResponse response = trainingService.createTraining(TrainingCreate.builder()
                .email("test@email.com")
                .workoutEa(3)
                .build());

        //then
        assertThat(response.getTrainingId()).isEqualTo(1L);
    }
}