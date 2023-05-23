package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.ConditionUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.ConditionPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.dto.request.SurveyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConditionService implements ConditionUseCase {
    private final LoadMemberPort loadMemberPort;
    private final ConditionPort conditionPort;
    @Override
    public void recordSurvey(SurveyRequest request) {
        Member member = loadMemberPort.loadMember(request.getEmail());
        Condition condition = Condition.builder()
                .member(member)
                .knee(request.getKnee())
                .wrist(request.getWrist())
                .elbow(request.getElbow())
                .lumbar(request.getLumbar())
                .neck(request.getNeck())
                .shoulder(request.getShoulder())
                .build();
        for (String hist : request.getHist()) {
            //TODO hist에 해당하는 질병 가져와서 medicalhist에 추가
            MedicalHist.builder()
                    .member(member)
//                    .disease()
                    .build();
        }

        conditionPort.saveCondition(condition);
    }
}
