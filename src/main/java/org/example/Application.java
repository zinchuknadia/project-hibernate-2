package org.example;

import org.example.dao.ActorRepository;
import org.example.dao.AddressRepository;
import org.example.dao.CategoryRepository;
import org.example.dao.CityRepository;
import org.example.dao.CountryRepository;
import org.example.dao.CustomerRepository;
import org.example.dao.FilmRepository;
import org.example.dao.FilmTextRepository;
import org.example.dao.InventoryRepository;
import org.example.dao.LanguageRepository;
import org.example.dao.PaymentRepository;
import org.example.dao.RentalRepository;
import org.example.dao.StaffRepository;
import org.example.dao.StoreRepository;

import org.example.domain.Actor;
import org.example.domain.Category;
import org.example.domain.Customer;
import org.example.domain.Language;
import org.example.domain.Feature;
import org.example.domain.Film;
import org.example.domain.Rating;
import org.example.domain.FilmText;
import org.example.domain.Store;
import org.example.domain.Inventory;
import org.example.domain.Staff;
import org.example.domain.Rental;
import org.example.domain.Payment;
import org.example.domain.City;
import org.example.domain.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    private final ActorRepository actorRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CustomerRepository customerRepository;
    private final FilmRepository filmRepository;
    private final FilmTextRepository filmTextRepository;
    private final InventoryRepository inventoryRepository;
    private final LanguageRepository languageRepository;
    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;

    public Application() {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

        actorRepository = new ActorRepository(sessionFactory);
        addressRepository = new AddressRepository(sessionFactory);
        categoryRepository = new CategoryRepository(sessionFactory);
        cityRepository = new CityRepository(sessionFactory);
        countryRepository = new CountryRepository(sessionFactory);
        customerRepository = new CustomerRepository(sessionFactory);
        filmRepository = new FilmRepository(sessionFactory);
        filmTextRepository = new FilmTextRepository(sessionFactory);
        inventoryRepository = new InventoryRepository(sessionFactory);
        languageRepository = new LanguageRepository(sessionFactory);
        paymentRepository = new PaymentRepository(sessionFactory);
        rentalRepository = new RentalRepository(sessionFactory);
        staffRepository = new StaffRepository(sessionFactory);
        storeRepository = new StoreRepository(sessionFactory);
    }

    public void run() {
        Customer customer = createCustomer();
        customerReturnInventoryToStore();
        customerRentInventory(customer);
        addNewFilmForRent();
    }

    private void addNewFilmForRent() {
        try (Session session = MySessionFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Language language = languageRepository.getItems(0, 21).stream().unordered().findAny().get();
            List<Category> categories = categoryRepository.getItems(0, 5);
            List<Actor> actors = actorRepository.getItems(0, 20);

            Set<Feature> features = new HashSet<>();
            features.add(Feature.TRAILERS);
            features.add(Feature.BEHIND_THE_SCENES);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.NC17);
            film.setSpecialFeatures(features);
            film.setLength((short) 251);
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.TEN);
            film.setLanguage(language);
            film.setDescription("This is a film");
            film.setTitle("Terminator");
            film.setRentalDuration((byte) 35);
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setYear(Year.now());
            filmRepository.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmText.setDescription("This is a film text");
            filmText.setTitle("Terminator");
            filmTextRepository.save(filmText);

            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = MySessionFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Film film = filmRepository.getFirstAvailableFilmForRent();
            Store store = storeRepository.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryRepository.save(inventory);

            Staff staff = store.getManagerStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalRepository.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(74.34));
            payment.setStaff(staff);
            paymentRepository.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = MySessionFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalRepository.getAnyUnreturnedItem();
            rental.setReturnDate(LocalDateTime.now());

            rentalRepository.update(rental);

            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = MySessionFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Store store = storeRepository.getItems(0, 1).get(0);
            City city = cityRepository.getByName("Kragujevac");

            Address address = new Address();
            address.setAddress("Index str, 24");
            address.setPhone("134-534-3536");
            address.setCity(city);
            address.setDistrict("Main");
            addressRepository.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail("test@test.com");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName("John");
            customer.setLastName("Martin");
            customerRepository.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }
}
