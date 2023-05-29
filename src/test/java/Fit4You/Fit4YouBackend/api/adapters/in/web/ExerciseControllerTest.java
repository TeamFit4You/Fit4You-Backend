package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.ConditionJpaRepository;
import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.DiseaseJpaRepository;
import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.HistJpaRepository;
import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.MemberJpaRepository;
import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training.ExerciseJpaRepository;
import Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training.ExercisePersistenceAdapter;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.dto.request.ExerciseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExerciseControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ExercisePersistenceAdapter adapter;
    @Autowired ExerciseJpaRepository jpaRepository;
    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired MemberJpaRepository memberJpaRepository;
    @Autowired ConditionJpaRepository conditionJpaRepository;
    @Autowired HistJpaRepository histJpaRepository;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach(){
        jpaRepository.deleteAll();
        histJpaRepository.deleteAll();
        diseaseJpaRepository.deleteAll();
        memberJpaRepository.deleteAll();
        conditionJpaRepository.deleteAll();

    }


    @Test
    @DisplayName("모든운동 전체 조회 요청:성공 - 과거병력 없음")
    void getExercises() throws Exception {

        //given
        List<String> names = new ArrayList<>(List.of("손목","허리디스크","목","척추관협착증"));
        List<String> parts = new ArrayList<>(List.of("손목","허리","목","허리"));
        List<Disease> diseases = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            diseases.add(Disease.builder()
                    .name(names.get(i % 4))
                    .relatedPart(parts.get(i % 4))
                    .build());
        }
        diseaseJpaRepository.saveAll(diseases);

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            exercises.add(Exercise.builder()
                    .disease(diseases.get(i%4))
                    .setEa(3)
                    .detail("상세내용" + i)
                    .videoLink("영상링크" + i)
                    .name(names.get(i%4)+"운동"+i)
                    .build());
        }
        jpaRepository.saveAll(exercises);

        Member member = Member.builder()
                .email("test@email.com")
                .password("password1234")
                .build();
        memberJpaRepository.save(member);
        Conditions conditions = Conditions.builder()
                .member(member).lumbar(5f).wrist(1f).neck(0f).knee(0f).elbow(0f).shoulder(0f)
                .build();
        conditionJpaRepository.save(conditions);

        Exercise first = exercises.get(1); // 손목 i%4 = 0,허리디스크 i%4 =1 ,목 i%4=2, 척추관협착증 i%4=3 으로 저장되어있음


        String json = objectMapper.writeValueAsString(
                ExerciseRequest.builder()
                        .email(member.getEmail())
                        .part("전체")
                        .build());

        //expected
        mockMvc.perform(get("/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].part").value(first.getDisease().getRelatedPart()))
                .andExpect(jsonPath("$.[0].detail").value(first.getDetail()))
                .andExpect(jsonPath("$.[0].diseaseName").value(first.getDisease().getName()))
                .andDo(print());
    }
    @Test
    @DisplayName("모든운동 전체 조회 요청:성공 - 과거병력 있음")
    void getExercises_hist() throws Exception {

        //given
        List<String> names = new ArrayList<>(List.of("손목","허리디스크","목","척추관협착증"));
        List<String> opposites = new ArrayList<>(List.of("","척추관협착증","","허리디스크"));
        List<String> parts = new ArrayList<>(List.of("손목","허리","목","허리"));
        List<Disease> diseases = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            diseases.add(Disease.builder()
                    .name(names.get(i % 4))
                    .relatedPart(parts.get(i % 4))
                    .opposite(opposites.get(i))
                    .build());
        }
        diseaseJpaRepository.saveAll(diseases);

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            exercises.add(Exercise.builder()
                    .disease(diseases.get(i%4))
                    .setEa(3)
                    .detail("상세내용" + i)
                    .videoLink("영상링크" + i)
                    .name(names.get(i%4)+"운동"+i)
                    .build());
        }
        jpaRepository.saveAll(exercises);

        Member member = Member.builder()
                .email("test@email.com")
                .password("password1234")
                .build();
        memberJpaRepository.save(member);
        Conditions conditions = Conditions.builder()
                .member(member).lumbar(5f).wrist(1f).neck(0f).knee(0f).elbow(0f).shoulder(0f)
                .build();
        conditionJpaRepository.save(conditions);
        MedicalHist hist = MedicalHist.builder()
                .member(member)
                .disease(diseaseJpaRepository.getByName("척추관협착증").get())
                .build();
        histJpaRepository.save(hist);
        Exercise first = exercises.get(3); // 손목 i%4 = 0,허리디스크 i%4 =1 ,목 i%4=2, 척추관협착증 i%4=3 으로 저장되어있음


        String json = objectMapper.writeValueAsString(
                ExerciseRequest.builder()
                        .email(member.getEmail())
                        .part("전체")
                        .build());

        //expected
        mockMvc.perform(get("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].part").value(first.getDisease().getRelatedPart()))
                .andExpect(jsonPath("$.[0].detail").value(first.getDetail()))
                .andExpect(jsonPath("$.[0].diseaseName").value(first.getDisease().getName()))
                .andDo(print());
    }
    @Test
    @DisplayName("특정 부위 운동 전체 조회 요청:성공 - 과거병력 없음")
    void getExercises_part() throws Exception {

        //given
        List<String> names = new ArrayList<>(List.of("손목","허리디스크","목","척추관협착증"));
        List<String> parts = new ArrayList<>(List.of("손목","허리","목","허리"));
        List<Disease> diseases = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            diseases.add(Disease.builder()
                    .name(names.get(i % 4))
                    .relatedPart(parts.get(i % 4))
                    .build());
        }
        diseaseJpaRepository.saveAll(diseases);

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            exercises.add(Exercise.builder()
                    .disease(diseases.get(i%4))
                    .setEa(3)
                    .detail("상세내용" + i)
                    .videoLink("영상링크" + i)
                    .name(names.get(i%4)+"운동"+i)
                    .build());
        }
        jpaRepository.saveAll(exercises);

        Member member = Member.builder()
                .email("test@email.com")
                .password("password1234")
                .build();
        memberJpaRepository.save(member);
        Conditions conditions = Conditions.builder()
                .member(member).lumbar(5f).wrist(1f).neck(0f).knee(0f).elbow(0f).shoulder(0f)
                .build();
        conditionJpaRepository.save(conditions);

        Exercise first = exercises.get(2); // 손목 i%4 = 0,허리디스크 i%4 =1 ,목 i%4=2, 척추관협착증 i%4=3 으로 저장되어있음


        String json = objectMapper.writeValueAsString(
                ExerciseRequest.builder()
                        .email(member.getEmail())
                        .part("목")
                        .build());

        //expected
        mockMvc.perform(get("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].part").value(first.getDisease().getRelatedPart()))
                .andExpect(jsonPath("$.[0].detail").value(first.getDetail()))
                .andExpect(jsonPath("$.[0].diseaseName").value(first.getDisease().getName()))
                .andDo(print());
    }

    @Test
    @DisplayName("id로 운동관련 정보 요청:성공")
    void getInfo() throws Exception {
        //given
        List<String> names = new ArrayList<>(List.of("손목","허리디스크","목","척추관협착증"));
        List<String> parts = new ArrayList<>(List.of("손목","허리","목","허리"));
        List<Disease> diseases = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            diseases.add(Disease.builder()
                    .name(names.get(i % 4))
                    .relatedPart(parts.get(i % 4))
                    .build());
        }
        diseaseJpaRepository.saveAll(diseases);

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            exercises.add(Exercise.builder()
                    .disease(diseases.get(i%4))
                    .setEa(3)
                    .detail("상세내용" + i)
                    .videoLink("영상링크" + i)
                    .name(names.get(i%4)+"운동"+i)
                    .build());
        }
        jpaRepository.saveAll(exercises);

        Member member = Member.builder()
                .email("test@email.com")
                .password("password1234")
                .build();
        memberJpaRepository.save(member);
        Conditions conditions = Conditions.builder()
                .member(member).lumbar(5f).wrist(1f).neck(0f).knee(0f).elbow(0f).shoulder(0f)
                .build();
        conditionJpaRepository.save(conditions);

        Exercise first = exercises.get(2); // 손목 i%4 = 0,허리디스크 i%4 =1 ,목 i%4=2, 척추관협착증 i%4=3 으로 저장되어있음

        //expected
        mockMvc.perform(get("/exercises/info/{id}",first.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(first.getName()))
                .andExpect(jsonPath("$.part").value(first.getDisease().getRelatedPart()))
                .andExpect(jsonPath("$.detail").value(first.getDetail()))
                .andExpect(jsonPath("$.diseaseName").value(first.getDisease().getName()))
                .andDo(print());
    }

}