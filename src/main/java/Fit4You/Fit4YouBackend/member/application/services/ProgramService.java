package Fit4You.Fit4YouBackend.member.application.services;

import Fit4You.Fit4YouBackend.member.application.ports.in.ProgramUseCase;
import Fit4You.Fit4YouBackend.member.application.ports.out.LoadMemberPort;
import Fit4You.Fit4YouBackend.member.application.ports.out.program.ExercisePort;
import Fit4You.Fit4YouBackend.member.application.ports.out.program.ProgramPort;
import Fit4You.Fit4YouBackend.member.application.ports.out.program.WorkoutPort;
import Fit4You.Fit4YouBackend.member.domains.Exercise;
import Fit4You.Fit4YouBackend.member.domains.Member;
import Fit4You.Fit4YouBackend.member.domains.Program;
import Fit4You.Fit4YouBackend.member.domains.Workout;
import Fit4You.Fit4YouBackend.member.dto.request.ProgramCreate;
import Fit4You.Fit4YouBackend.member.dto.response.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService implements ProgramUseCase {

    private final ProgramPort programPort;
    private final WorkoutPort workoutPort;
    private final ExercisePort exercisePort;
    private final LoadMemberPort loadMemberPort;


    /**
     * result 는 나중에 업데이트..?
     */
    @Override
    @Transactional
    public ProgramResponse createProgram(ProgramCreate programCreate) {
        Member member = loadMemberPort.loadMember(programCreate.getEmail());
        Program program = Program.builder()
                .member(member)
                .setNum(programCreate.getPnum())
                .build();

        Long programId = programPort.create(program);

        List<Exercise> exercises = exercisePort.getAll();

        //TODO setNum이 exercise의 총 개수보다 커서는 안될듯; setNum 제한조건(constraint) 필요
        for (int i = 0; i < programCreate.getPnum(); i++) {
            Exercise exercise = exercises.get(i);//TODO 우선순위 기반으로 바꿀 것
            Workout workout = Workout.builder()
                    .setNum(programCreate.getWnum())
                    .program(program)
                    .exercise(exercise)
                    .build();
            workoutPort.create(workout);//TODO 리스트로 모았다가 한번에 쿼리로 전환
        }

        return ProgramResponse.builder()
                .programId(programId)
                .build();
    }


}
