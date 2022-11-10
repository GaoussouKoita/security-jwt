package ml.pic.tech.security.token.security.service;


import ml.pic.tech.security.token.security.entity.ChangePassword;
import ml.pic.tech.security.token.security.entity.Role;
import ml.pic.tech.security.token.security.entity.Utilisateur;
import ml.pic.tech.security.token.security.repository.RoleRepository;
import ml.pic.tech.security.token.security.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AccountService implements UserDetailsService {


    @Autowired
    private UtilisateurRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Utilisateur addUtilisateur(Utilisateur utilisateur) {

        utilisateur.setPassword(passwordEncoder
                .encode(utilisateur.getPassword()));
        return repository.save(utilisateur);
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }


    public Utilisateur findByUsername(String email) {
        return repository.findByEmail(email);
    }

    private Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public List<Utilisateur> utilisateurList() {
        return repository.findAll();
    }


    public Page<Utilisateur> utilisateurPage(int page) {
        return repository.findAll(PageRequest.of(page, 5, Sort.by("prenom").and(Sort.by("nom"))));
    }

    public List<Role> roleList() {
        return roleRepository.findAll(Sort.by("roleName"));
    }

    public Utilisateur findById(Long id) {
        return repository.findById(id).get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Utilisateur currentUtilisateur() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Utilisateur utilisateur = repository.findByEmail(email);
        return utilisateur;
    }

    public void updatePassword(ChangePassword passwordNew) {
        Utilisateur currentUtilisateur = currentUtilisateur();

        boolean passwordMatch = passwordEncoder
                .matches(passwordNew.getOldPassword(), currentUtilisateur.getPassword());
        if (passwordMatch) {
            repository.updatePasswordByEmail(currentUtilisateur.getEmail(),
                    passwordEncoder.encode(passwordNew.getNewPassword()));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = findByUsername(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(utilisateur.getEmail(), utilisateur.getPassword(), authorities);
    }
}
