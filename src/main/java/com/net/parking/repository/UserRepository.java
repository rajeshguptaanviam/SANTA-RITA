package com.net.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.net.parking.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 public User findByEmail(String email);
	 public User findById(String id);
	 public User findByEmailAndPassword(String email, String password);
}
