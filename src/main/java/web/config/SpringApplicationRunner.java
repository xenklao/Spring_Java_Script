package web.config;

//import web.model.Role;
//import web.model.User;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class SpringApplicationRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(SpringApplicationRunner.class);
//    private final UserDAO userDAO;
//    private final RoleDAO roleDAO;
//    private final PasswordEncoder passwordEncoder;
//
//
//
//    public SpringApplicationRunner(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
//        this.userDAO = userDAO;
//        this.roleDAO = roleDAO;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Bean
//    CommandLineRunner initDatabase(RoleDAO roleRepository,
//                                   UserDAO userRepository,
//                                   PasswordEncoder passwordEncoder){
//        return args -> {
////            Role roleAdmin = new Role("ROLE_ADMIN");
////            Role roleUser = new Role("ROLE_USER");
////
////            log.info("Preloading " + roleRepository.save(roleAdmin));
////            log.info("Preloading " + roleRepository.save(roleUser));
//
//            Set<Role> roles1 = new HashSet<>();
//            roles1.add(roleDAO.findById(1L).orElse(null));
//            Set<Role> roles2 = new HashSet<>();
//            roles2.add(roleDAO.findById(1L).orElse(null));
//            roles2.add(roleDAO.findById(2L).orElse(null));
//
//            log.info("Preloading " + userRepository.save(new User("Vasja", "Utkin", (byte) 49, "admin@mail.com", "admin",
//                    "admin", roles2
//                   )));
//        };
//        }
//
//}
