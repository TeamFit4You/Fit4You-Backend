package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    PriorityService priorityService;

    TrainingService trainingService;
    List<Exercise> exercises;

    @BeforeEach
    void beforeEach() {
        priorityService = new PriorityService();
        trainingService= new TrainingService(trainingPort,workoutPort,exercisePort,loadMemberPort,priorityService);
        String[] parts = {"어깨", "팔꿈치", "무릎", "손목", "허리", "목"};
        exercises = new ArrayList<>();

        Long idx = 0L;
        for (String part : parts) {
            Exercise exercise = Exercise.builder()
                    .disease(Disease.builder()
                            .name(part + "일반")
                            .relatedPart(part)
                            .build())
                    .name(part + "운동")
                    .setEa(3)
                    .build();
            exercise.setIdOnlyForTest(idx++);
            exercises.add(exercise);

        }
    }
    @Test
    @DisplayName("운동추천")
    void recommendWorkout() {

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
        Conditions conditions = Conditions.builder()
                .member(member)
                .knee(1f)
                .elbow(2f)
                .wrist(20f)
                .lumbar(4f)
                .neck(5f)
                .shoulder(6f)
                .build();
        member.setConditionOnlyForTest(conditions);
        //TODO hist추가

        given(exercisePort.getAll()).willReturn(exercises);

        //when
        List<Exercise> results = trainingService.getExercisesByPriority(member);

        //then
        assertThat(results.get(0).getName()).isEqualTo("손목운동");
        assertThat(results.get(1).getName()).isEqualTo("어깨운동");
        assertThat(results.get(2).getName()).isEqualTo("목운동");
    }

    @Test
    @DisplayName("추천목록 생성:성공")
    void recommend() {

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
        Conditions conditions = Conditions.builder()
                .member(member)
                .knee(1f)
                .elbow(2f)
                .wrist(20f)
                .lumbar(4f)
                .neck(5f)
                .shoulder(6f)
                .build();
        member.setConditionOnlyForTest(conditions);
        //TODO hist추가

        //stubbing
        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);
        given(trainingPort.create(any())).willReturn(1L);
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        TrainingResponse response = trainingService.createRecommend(RecommendCreate.builder()
                .email("test@email.com")
                .build());

        List<Long> workoutIds = response.getWorkoutIds();
        //then
        assertThat(workoutIds.size()).isEqualTo(3);

    }
    @Test
    @DisplayName("운동생성")
    void createWorkout() {
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();

        //stubbing
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        List<Exercise> results = trainingService.getExercisesById(List.of(1L, 3L, 5L));

        //then
        assertThat(results.get(0).getName()).isEqualTo("팔꿈치운동");
        assertThat(results.get(1).getName()).isEqualTo("손목운동");
        assertThat(results.get(2).getName()).isEqualTo("목운동");
    }
    @Test
    @DisplayName("운동목록 생성:성공")
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

        //stubbing
        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);
        given(trainingPort.create(any())).willReturn(1L);
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        TrainingResponse response = trainingService.createTraining(TrainingCreate.builder()
                .email("test@email.com")
                .selects(List.of(1L,3L,5L))
                .build());

        List<Long> workoutIds = response.getWorkoutIds();
        //then
        assertThat(workoutIds.size()).isEqualTo(3);
    }
}