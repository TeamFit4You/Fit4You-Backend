package Fit4You.Fit4YouBackend.api.domains.member;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MedicalHist> medicalHists= new ArrayList();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Condition condition;

    @Builder
    public Member(String email, String password){
        this.email = email;
        this.password = password;
    }
<<<<<<< HEAD
=======
    
>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
    /*주의! TEST용*/
    public void setConditionOnlyForTest(Condition condition){
        this.condition = condition;
    }
<<<<<<< HEAD
=======

>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
}
