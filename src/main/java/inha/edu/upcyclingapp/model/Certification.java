package inha.edu.upcyclingapp.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "image_path", nullable = false, length = 255)
    private String imagePath;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Certification(Long userId, Long missionId, String imagePath, String category) {
        this.userId = userId;
        this.missionId = missionId;
        this.imagePath = imagePath;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}