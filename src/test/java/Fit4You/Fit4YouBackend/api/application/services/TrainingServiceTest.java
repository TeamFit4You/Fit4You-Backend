package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
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

    TrainingService trainingService;
    List<Exercise> exercises;

    @BeforeEach
    void beforeEach() {
        trainingService= new TrainingService(trainingPort,workoutPort,exercisePort,loadMemberPort);
        String[] parts = {"shoulder", "elbow", "ankle", "wrist", "lumbar", "neck"};
        exercises = new ArrayList<>();
<<<<<<< HEAD
=======

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
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
<<<<<<< HEAD
=======

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
        }
    }
    @Test
    @DisplayName("운동추천")
<<<<<<< HEAD
    void recommendWorkout() {
=======

    void recommendWorkout() {

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
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
        member.setConditionOnlyForTest(condition);
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
    @DisplayName("추천목록 생성:성공")
    void recommend() {
<<<<<<< HEAD
=======

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
        //given
        SignInRequest request = SignInRequest.builder()
                .email("test@email.com")
                .password("testPw")
                .build();
        Member member = Member.builder()
                .email(request.getEmail())
                .password(PasswordEncoder.encode(request.getPassword()))
                .build();
<<<<<<< HEAD
=======

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
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
        member.setConditionOnlyForTest(condition);
        //TODO hist추가

        //stubbing
        given(loadMemberPort.loadMember(any(String.class))).willReturn(member);
        given(trainingPort.create(any())).willReturn(1L);
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        TrainingResponse response = trainingService.createRecommend(RecommendCreate.builder()
                .email("test@email.com")
                .build());

        //then
        assertThat(response.getTrainingId()).isEqualTo(1L);
    }
    @Test
    @DisplayName("운동생성")
    void createWorkout() {
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
        given(exercisePort.getAll()).willReturn(exercises);

        //when
        List<Exercise> results = trainingService.getExercisesById(List.of(1L, 3L, 5L));

        //then
        assertThat(results.get(0).getName()).isEqualTo("elbow운동");
        assertThat(results.get(1).getName()).isEqualTo("wrist운동");
        assertThat(results.get(2).getName()).isEqualTo("neck운동");
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

        //then
        assertThat(response.getTrainingId()).isEqualTo(1L);
    }
}