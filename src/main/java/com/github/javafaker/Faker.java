package com.github.javafaker;

import com.github.javafaker.service.FakeSeed;
import com.github.javafaker.service.FakeValuesInterface;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 */
public class Faker {
    private final RandomService randomService;
    private final FakeValuesService fakeValuesService;
    private final Map<String, Object> fakeObjects;

    public Faker() {
        this(Locale.ENGLISH);
    }

    public Faker(Locale locale) {
        this(locale, null);
    }

    public Faker(Random random) {
        this(Locale.ENGLISH, random);
    }

    public Faker(Locale locale, Random random) {
        this.randomService = new RandomService(random);
        this.fakeValuesService = new FakeValuesService(locale, randomService);
        this.fakeObjects = new HashMap<String, Object>();
        init();

    }


    private void init(){
        this.fakeObjects.put("alphaNumeric", new AlphaNumeric(randomService));
        this.fakeObjects.put("app", new App(this));
        this.fakeObjects.put("artist", new Artist(this));
        this.fakeObjects.put("avatar", new Avatar(this));
        this.fakeObjects.put("lorem", new Lorem(this));
        this.fakeObjects.put("music", new Music(this));
        this.fakeObjects.put("name", new Name(this));
        this.fakeObjects.put("number", new Number(this));
        this.fakeObjects.put("internet", new Internet(this));
        this.fakeObjects.put("phoneNumber", new PhoneNumber(this));
        this.fakeObjects.put("pokemon", new Pokemon(this));
        this.fakeObjects.put("address", new Address(this));
        this.fakeObjects.put("book", new Book(this));
        this.fakeObjects.put("business", new Business(this));
        this.fakeObjects.put("chuckNorris", new ChuckNorris(this));
        this.fakeObjects.put("color", new Color(this));
        this.fakeObjects.put("idNumber", new IdNumber(this));
        this.fakeObjects.put("hacker", new Hacker(this));
        this.fakeObjects.put("company", new Company(this));
        this.fakeObjects.put("crypto", new Crypto(this));
        this.fakeObjects.put("commerce", new Commerce(this));
        this.fakeObjects.put("currency", new Currency(this));
        this.fakeObjects.put("options", new Options(this));
        this.fakeObjects.put("code", new Code(this));
        this.fakeObjects.put("file", new File(this));
        this.fakeObjects.put("finance", new Finance(this));
        this.fakeObjects.put("food", new Food(this));
        this.fakeObjects.put("gameOfThrones", new GameOfThrones(this));
        this.fakeObjects.put("dateAndTime", new DateAndTime(this));
        this.fakeObjects.put("demographic", new Demographic(this));
        this.fakeObjects.put("dog", new Dog(this));
        this.fakeObjects.put("educator", new Educator(this));
        this.fakeObjects.put("shakespeare", new Shakespeare(this));
        this.fakeObjects.put("slackEmoji", new SlackEmoji(this));
        this.fakeObjects.put("space", new Space(this));
        this.fakeObjects.put("bool", new Bool(this));
        this.fakeObjects.put("beer", new Beer(this));
        this.fakeObjects.put("university", new University(this));
        this.fakeObjects.put("cat", new Cat(this));
        this.fakeObjects.put("stock", new Stock(this));
        this.fakeObjects.put("lordOfTheRings", new LordOfTheRings(this));
        this.fakeObjects.put("zelda", new Zelda(this));
        this.fakeObjects.put("harryPotter", new HarryPotter(this));
        this.fakeObjects.put("rockBand", new RockBand(this));
        this.fakeObjects.put("esports", new Esports(this));
        this.fakeObjects.put("friends", new Friends(this));
        this.fakeObjects.put("hipster", new Hipster(this));
        this.fakeObjects.put("job", new Job(this));
        this.fakeObjects.put("twinPeaks", new TwinPeaks(this));
        this.fakeObjects.put("rickAndMorty", new RickAndMorty(this));
        this.fakeObjects.put("yoda", new Yoda(this));
        this.fakeObjects.put("matz", new Matz(this));
        this.fakeObjects.put("witcher", new Witcher(this));
        this.fakeObjects.put("dragonBall", new DragonBall(this));
        this.fakeObjects.put("funnyName", new FunnyName(this));
        this.fakeObjects.put("hitchhikersGuideToTheGalaxy", new HitchhikersGuideToTheGalaxy(this));
        this.fakeObjects.put("hobbit", new Hobbit(this));
        this.fakeObjects.put("howIMetYourMother", new HowIMetYourMother(this));
        this.fakeObjects.put("leagueOfLegends", new LeagueOfLegends(this));
        this.fakeObjects.put("overwatch", new Overwatch(this));
        this.fakeObjects.put("robin", new Robin(this));
        this.fakeObjects.put("starTrek", new StarTrek(this));
        this.fakeObjects.put("weather", new Weather(this));
        this.fakeObjects.put("lebowski", new Lebowski(this));
        this.fakeObjects.put("medical", new Medical(this));
        this.fakeObjects.put("country", new Country(this));
    }


    public <T> T getFakeObject(String fakeObjectName){
        return (T) this.fakeObjects.get(fakeObjectName);
    }

    private void register(String key, Object value){
        this.fakeObjects.put(key, value);
    }

    /**
     * add fake fakeObjects
     * @param fakeValuesInterface
     */
    private void addFakeValues(FakeValuesInterface fakeValuesInterface){
        this.fakeValuesService.addFakeValues(fakeValuesInterface);
    }


    /**
     * add {@Link FakeSeed}
     * @param fakeSeed
     */
    public void addFakeSeed(FakeSeed fakeSeed){
        FakeValuesInterface fakeValuesInterface = fakeSeed.getFakeValuesInterfaces();
        Class<?> clazz = fakeSeed.getFakeClazz();

        if(fakeValuesService != null){
            this.addFakeValues(fakeValuesInterface);
        }

        if(clazz != null){
            this.register(clazz.getSimpleName(), initFakeObject(clazz));
        }
    }

    private Object initFakeObject(Class<?> clazz){
        Object o = null;
        try{
            Constructor<?> constructor = clazz.getConstructor(getClass());
            o = constructor.newInstance(this);
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return o;
    }

    /**
     * Constructs Faker instance with default argument.
     *
     * @return {@link Faker#Faker()}
     */
    public static Faker instance() {
        return new Faker();
    }

    /**
     * Constructs Faker instance with provided {@link Locale}.
     *
     * @param locale - {@link Locale}
     * @return {@link Faker#Faker(Locale)}
     */
    public static Faker instance(Locale locale) {
        return new Faker(locale);
    }

    /**
     * Constructs Faker instance with provided {@link Random}.
     *
     * @param random - {@link Random}
     * @return {@link Faker#Faker(Random)}
     */
    public static Faker instance(Random random) {
        return new Faker(random);
    }

    /**
     * Constructs Faker instance with provided {@link Locale} and {@link Random}.
     *
     * @param locale - {@link Locale}
     * @param random - {@link Random}
     * @return {@link Faker#Faker(Locale, Random)}
     */
    public static Faker instance(Locale locale, Random random) {
        return new Faker(locale, random);
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p>
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        return fakeValuesService.numerify(numberString);
    }



    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        return fakeValuesService.letterify(letterString);
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @param isUpper
     * @return
     */
    public String letterify(String letterString, boolean isUpper) {
        return fakeValuesService.letterify(letterString, isUpper);
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @return
     */
    public String bothify(String string) {
        return fakeValuesService.bothify(string);
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @param isUpper
     * @return
     */
    public String bothify(String string, boolean isUpper) {
        return fakeValuesService.bothify(string, isUpper);
    }

    /**
     * Generates a String that matches the given regular expression.
     */
    public String regexify(String regex) {
        return fakeValuesService.regexify(regex);
    }

    public RandomService random() {
        return this.randomService;
    }

    public Currency currency() {
        return getFakeObject("currency");

    }

    FakeValuesService fakeValuesService() {
        return this.fakeValuesService;
    }

    public AlphaNumeric alphaNumeric(){
        return getFakeObject("alphaNumeric");
    }

    public Ancient ancient() {
        return getFakeObject("ancient");
    }

    public App app() {
        return getFakeObject("app");
    }

    public Artist artist() {
        return getFakeObject("artist");
    }

    public Avatar avatar() {
        return getFakeObject("avatar");
    }

    public Music music() {
        return getFakeObject("music");
    }

    public Name name() {
        return getFakeObject("name");
    }

    public Number number() {
        return getFakeObject("number");
    }

    public Internet internet() {
        return getFakeObject("internet");
    }

    public PhoneNumber phoneNumber() {
        return getFakeObject("phoneNumber");
    }

    public Pokemon pokemon() {
        return getFakeObject("pokemon");
    }

    public Lorem lorem() {
        return getFakeObject("lorem");
    }

    public Address address() {
        return getFakeObject("address");
    }

    public Book book() {
        return getFakeObject("book");
    }

    public Business business() {
        return getFakeObject("business");
    }

    public ChuckNorris chuckNorris() {
        return getFakeObject("chuckNorris");
    }

    public Color color() {
        return getFakeObject("color");
    }

    public Commerce commerce() {
        return getFakeObject("commerce");
    }

    public Company company() {
        return getFakeObject("company");
    }

    public Crypto crypto() {
        return getFakeObject("crypto");
    }

    public Hacker hacker() {
        return getFakeObject("hacker");
    }

    public IdNumber idNumber() {
        return getFakeObject("idNumber");
    }

    public Options options() {
        return getFakeObject("options");
    }

    public Code code() {
        return getFakeObject("code");
    }

    public File file() {
        return getFakeObject("file");
    }

    public Finance finance() {
        return getFakeObject("finance");
    }

    public Food food() {
        return getFakeObject("food");
    }

    public GameOfThrones gameOfThrones() {
        return getFakeObject("gameOfThrones");
    }

    public DateAndTime date() {
        return getFakeObject("dateAndTime");
    }

    public Demographic demographic() {
        return getFakeObject("demographic");
    }

    public Dog dog() {
        return getFakeObject("dog");
    }

    public Educator educator() {
        return getFakeObject("educator");
    }

    public SlackEmoji slackEmoji() {
        return getFakeObject("slackEmoji");
    }

    public Shakespeare shakespeare() {
        return getFakeObject("shakespeare");
    }

    public Space space() {
        return getFakeObject("space");
    }

    public Superhero superhero() {
        return getFakeObject("superhero");
    }

    public Bool bool() {
        return getFakeObject("bool");
    }

    public Team team() {
        return getFakeObject("team");
    }

    public Beer beer() {
        return getFakeObject("beer");
    }

    public University university() {
        return getFakeObject("university");
    }

    public Cat cat() {
        return getFakeObject("cat");
    }

    public Stock stock() {
        return getFakeObject("stock");
    }

    public LordOfTheRings lordOfTheRings() {
        return getFakeObject("lordOfTheRings");
    }

    public Zelda zelda() {
        return getFakeObject("zelda");
    }

    public HarryPotter harryPotter() {
        return getFakeObject("harryPotter");
    }

    public RockBand rockBand() {
        return getFakeObject("rockBand");
    }

    public Esports esports() {
        return getFakeObject("esports");
    }

    public Friends friends() {
        return getFakeObject("friends");
    }

    public Hipster hipster() {
        return getFakeObject("hipster");
    }

    public Job job() {
        return getFakeObject("job");
    }

    public TwinPeaks twinPeaks() {
        return getFakeObject("twinPeaks");
    }

    public RickAndMorty rickAndMorty() {
        return getFakeObject("rickAndMorty");
    }

    public Yoda yoda() {
        return getFakeObject("yoda");
    }

    public Matz matz() {
        return getFakeObject("matz");
    }

    public Witcher witcher() {
        return getFakeObject("witcher");
    }

    public DragonBall dragonBall() {
        return getFakeObject("dragonBall");
    }

    public FunnyName funnyName() {
        return getFakeObject("funnyName");
    }

    public HitchhikersGuideToTheGalaxy hitchhikersGuideToTheGalaxy() {
        return getFakeObject("hitchhikersGuideToTheGalaxy");
    }

    public Hobbit hobbit() {
        return getFakeObject("hobbit");
    }

    public HowIMetYourMother howIMetYourMother() {
        return getFakeObject("howIMetYourMother");
    }

    public LeagueOfLegends leagueOfLegends() {
        return getFakeObject("leagueOfLegends");
    }

    public Overwatch overwatch() {
        return getFakeObject("overwatch");
    }

    public Robin robin() {
        return getFakeObject("robin");
    }

    public StarTrek starTrek() {
        return getFakeObject("starTrek");
    }

    public Weather weather() {
        return getFakeObject("weather");
    }

    public Lebowski lebowski() {
        return getFakeObject("lebowski");
    }

    public Medical medical(){return getFakeObject("medical;");}

    public Country country(){ return getFakeObject("country;");}

    public String resolve(String key) {
        return this.fakeValuesService.resolve(key, this, this);
    }

    /**
     * Allows the evaluation of native YML expressions to allow you to build your own.
     * <p>
     * The following are valid expressions:
     * <ul>
     * <li>#{regexify '(a|b){2,3}'}</li>
     * <li>#{regexify '\\.\\*\\?\\+'}</li>
     * <li>#{bothify '????','false'}</li>
     * <li>#{Name.first_name} #{Name.first_name} #{Name.last_name}</li>
     * <li>#{number.number_between '1','10'}</li>
     * </ul>
     *
     * @param expression (see examples above)
     * @return the evaluated string expression
     * @throws RuntimeException if unable to evaluate the expression
     */
    public String expression(String expression) {
        return this.fakeValuesService.expression(expression, this);
    }
}
