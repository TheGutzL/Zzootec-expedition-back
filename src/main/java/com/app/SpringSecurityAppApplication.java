package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init(UserRepository userRepository) {
	// return args -> {
	// /* CREATE PERMISSIONS */
	// PermissionEntity createPermission = PermissionEntity.builder()
	// .name("CREATE")
	// .build();

	// PermissionEntity readPermission = PermissionEntity.builder()
	// .name("READ")
	// .build();

	// PermissionEntity updatePermission = PermissionEntity.builder()
	// .name("UPDATE")
	// .build();

	// PermissionEntity deletePermission = PermissionEntity.builder()
	// .name("DELETE")
	// .build();

	// PermissionEntity refactorPermission = PermissionEntity.builder()
	// .name("REFACTOR")
	// .build();

	// RoleEntity roleAdmin = RoleEntity.builder()
	// .roleEnum(RoleEnum.ADMIN)
	// .permissionList(Set.of(createPermission, readPermission, updatePermission,
	// deletePermission,
	// refactorPermission))
	// .build();

	// RoleEntity roleUser = RoleEntity.builder()
	// .roleEnum(RoleEnum.USER)
	// .permissionList(Set.of(createPermission, readPermission))
	// .build();

	// RoleEntity roleInvited = RoleEntity.builder()
	// .roleEnum(RoleEnum.INVITED)
	// .permissionList(Set.of(readPermission))
	// .build();

	// RoleEntity roleDeveloper = RoleEntity.builder()
	// .roleEnum(RoleEnum.DEVELOPER)
	// .permissionList(Set.of(createPermission, readPermission, updatePermission,
	// deletePermission,
	// refactorPermission))
	// .build();

	// /* Create USERS */
	// UserEntity userSantiago = UserEntity.builder()
	// .username("Santiago")
	// .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
	// .isEnabled(true)
	// .accountNoExpired(true)
	// .accountNoLocked(true)
	// .credentialNoExpired(true)
	// .roles(Set.of(roleAdmin))
	// .build();

	// UserEntity userDaniel = UserEntity.builder()
	// .username("Daniel")
	// .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
	// .isEnabled(true)
	// .accountNoExpired(true)
	// .accountNoLocked(true)
	// .credentialNoExpired(true)
	// .roles(Set.of(roleUser))
	// .build();

	// UserEntity userAndrea = UserEntity.builder()
	// .username("Andrea")
	// .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
	// .isEnabled(true)
	// .accountNoExpired(true)
	// .accountNoLocked(true)
	// .credentialNoExpired(true)
	// .roles(Set.of(roleInvited))
	// .build();

	// UserEntity userAngie = UserEntity.builder()
	// .username("Angie")
	// .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
	// .isEnabled(true)
	// .accountNoExpired(true)
	// .accountNoLocked(true)
	// .credentialNoExpired(true)
	// .roles(Set.of(roleDeveloper))
	// .build();

	// userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea,
	// userAngie));
	// };
	// }

}
