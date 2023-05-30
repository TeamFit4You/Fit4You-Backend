package Fit4You.Fit4YouBackend.api.member.application.service;

import Fit4You.Fit4YouBackend.api.member.application.port.in.ConditionUseCase;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.ConditionPort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.DiseasePort;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.HistPort;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.member.domains.Conditions;
import Fit4You.Fit4YouBackend.api.member.domains.MedicalHist;
import Fit4You.Fit4YouBackend.api.member.domains.Member;
import Fit4You.Fit4YouBackend.api.member.dto.request.SurveyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConditionService implements ConditionUseCase {
    private final LoadMemberPort loadMemberPort;
    private final ConditionPort conditionPort;
    private final DiseasePort diseasePort;
    private final HistPort histPort;
    @Override
    @Transactional
    public void recordSurvey(SurveyRequest request) {
        Member member = loadMemberPort.loadMember(request.getEmail());
        Optional<Conditions> optional = conditionPort.getByMember(member.getId());

        List<MedicalHist> medicalHists = histPort.getByMemberId(member.getId());
        Set<String> names = medicalHists.stream().map(h -> h.getDisease().getName()).collect(Collectors.toSet());
        for (String hist : request.getHist()) {
            if(!names.remove(hist)){
                histPort.save(MedicalHist.builder()
                        .member(member)
                        .disease(diseasePort.getByName(hist))
                        .build());
            }
        }
        for (MedicalHist hist : medicalHists){
            if(names.contains(hist.getDisease().getName())){
                histPort.deleteById(hist.getId());
            }
        }

        if (optional.isEmpty()){
            Conditions conditions = Conditions.builder()
                    .member(member)
                    .knee(request.getKnee())
                    .wrist(request.getWrist())
                    .elbow(request.getElbow())
                    .lumbar(request.getLumbar())
                    .neck(request.getNeck())
                    .shoulder(request.getShoulder())
                    .build();
            conditionPort.saveCondition(conditions);
        }else{
            optional.get().modify(request);
        }


    }
}
