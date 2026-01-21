package com.example.demo.model.jpa;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.example.demo.model.IntegerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.JdbcType;
//import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements UserDetails, IntegerEntity {

	public enum Role {
		ADMINISTRATOR, REDACTOR, TRANSLATOR, READER;

		public static boolean isAdminRole(Role role) {
			return role == ADMINISTRATOR || role == REDACTOR || role == TRANSLATOR;
		}

		public static boolean isClientRole(Role role) {
			return role == READER;
		}
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 64, nullable = false)
	private String name;

	@Column(name = "login", length = 64, nullable = false, unique = true)
	private String login;

	@Column(name = "password", length = 128, nullable = false)
	private String password;

	@Column(name = "email", length = 80, unique = true)
	private String email;

	@Column(name = "bio", length = 500, nullable = false)
	private String bio;

	@Column(name = "role", columnDefinition = "user_role", nullable = false)
	@Enumerated(EnumType.STRING)
//	@JdbcType(PostgreSQLEnumJdbcType.class)
	private Role role;

	@Column(name = "created_at", updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return "";
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
