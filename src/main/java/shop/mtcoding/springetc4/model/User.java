package shop.mtcoding.springetc4.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity // javax 꺼
@NoArgsConstructor
@Table(name = "user_tb")
@Getter
public class User {
    @Id // javax 꺼
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String username;
    @JsonIgnore // 이거 붙이면 붙인애는 json은 파싱 하지 말라고 하는 것
    private String password;
    private String email;
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Long id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;

        
    }

    public void update(String password, String email) { // 의미있는 데이터는 setter가 아니라 메서드로 의미있게 초기화 해라
        this.password = password;
        this.email = email;
    }
    
    public void passwordUpdate(){

    }
}
