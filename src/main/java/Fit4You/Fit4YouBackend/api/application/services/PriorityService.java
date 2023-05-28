package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.config.Part;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PriorityService {

    public void sortByPriority(Member member, List<Exercise> exercises){
        exercises.sort(getComparator(getPriority(member, exercises)));
    }
    public Map<String, Float> getPriority(Member member, List<Exercise> exercises){

        Map<String, Float> weightMap = new HashMap<>();

        //현재 conditions를 가중치에 반영
        Conditions conditions = member.getConditions();
        weightMap.put(Part.목.label(), conditions.getNeck());
        weightMap.put(Part.어깨.label(), conditions.getShoulder());
        weightMap.put(Part.허리.label(), conditions.getLumbar());
        weightMap.put(Part.손목.label(), conditions.getWrist());
        weightMap.put(Part.팔꿈치.label(), conditions.getElbow());
        weightMap.put(Part.무릎.label(), conditions.getKnee());

        // 과거병력이 있는 경우 처리
        List<MedicalHist> medicalHists = member.getMedicalHists();
        Float histWeight = 1f;
        Set<String> opposites = new HashSet<>();
        for (MedicalHist hist : medicalHists) {
            Disease disease = hist.getDisease();
            String part = disease.getRelatedPart();
            // 가중치 up
            weightMap.put(part,weightMap.get(part)+histWeight);

            // 상극인 질환 기록
            if(disease.getOpposite()!=null) {
                opposites.add(disease.getOpposite());
            }
        }


        // K,V = exercise, Float(운동, 가중치) 으로 hashmap 생성
        Map<String, Float> priority = exercises.stream()
                .collect(Collectors.toMap(Exercise::getName, e -> 0f));

        // 가중치를 우선순위에 반영
        for (Exercise e : exercises) {
            Float weight = weightMap.get(e.getDisease().getRelatedPart());

            //과거 병력이 있는 질환과 상극인 질환에 관한 운동인 경우 가중치 -1
            if(opposites.contains(e.getDisease().getName())){
                priority.put(e.getName(), -1f);
            }else {
                priority.put(e.getName(), weight);
            }
        }

        // TODO? 최근에 수행했던 운동인 경우 우선순위 소폭 down


        //선형탐색? 해시?

        return priority;
    }


    public static Comparator<Exercise> getComparator(Map<String, Float> priority) {

        return new Comparator<>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                float diff = priority.get(o2.getName()) - priority.get(o1.getName()); // 내림차순
                if (diff < 0) return -1;
                else if (diff == 0) return 0;
                else return 1;
            }
        };
    }
}
