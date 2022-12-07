package zahar.labs.weblab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zahar.labs.weblab.models.Person;
import zahar.labs.weblab.repositories.PeopleRepository;
import zahar.labs.weblab.security.PersonDetails;

import java.util.Optional;

@Service
public class PeopleDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = peopleRepository.findByUsername(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("user not found");

        return new PersonDetails(user.get());
    }
}
