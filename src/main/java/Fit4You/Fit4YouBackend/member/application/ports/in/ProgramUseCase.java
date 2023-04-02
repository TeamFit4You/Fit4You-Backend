package Fit4You.Fit4YouBackend.member.application.ports.in;

import Fit4You.Fit4YouBackend.member.dto.request.ProgramCreate;
import Fit4You.Fit4YouBackend.member.dto.response.ProgramResponse;

public interface ProgramUseCase {

    ProgramResponse createProgram(ProgramCreate programCreate);
}
