package Fit4You.Fit4YouBackend.member.adapters.in.web;

import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.member.application.ports.in.ProgramUseCase;
import Fit4You.Fit4YouBackend.member.dto.request.ProgramCreate;
import Fit4You.Fit4YouBackend.member.dto.response.ProgramResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProgramController {

    private final ProgramUseCase programUseCase;

//    @Auth
    @PostMapping("/programs/create")
    public ProgramResponse startProgram(@RequestBody @Valid ProgramCreate request){
        return programUseCase.createProgram(request);
    }

}
