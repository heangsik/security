package kr.co.yhs.security.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String passwrod;
    private String role;

    public void setPasswordEncod(PasswordEncoder encoder) {
        this.passwrod = encoder.encode(this.passwrod);
    }
}
