* немного не по теме, при разработке нового проекта сначала пишу имплементацию, потом выковыриваю из нее абстракцию
* сперва все классы immutable, потом они могут стать mutable если это необходимо


* Не проверять больше одного объекта в одном тесте
* when write test
    1. code-first(legacy code,)
    2. test-first(new code, good design)
    3. write test every time a bug is found(immediately!!!)

TDD approach
1. (RED) Always start with the failing test, and always observe it failing.
* An example of writing a simple test case just to get you started would be:
    * The Low-Hanging Fruit. This rule says: "Start with something really simple. Implement an obvious test case."
      Writing a parameter-checking test for a function (no matter what the purpose of the function in question might be),
      or, when writing a parser, starting with the test case of passing an empty String to the parsing method and receiving null in return
    * The  Most  Informative  One. "it  doe``````s  not  matter  that  my  first  match  is  against  the  world champion - if I am going to win the whole tournament, I will have to beat him anyway".
      This is probably the test which you know you still do not know how to make pass. You will simply know which one that is.
    * First The Typical Case, Then Corner Cases.
      When implementing a vending machine, begin with a client inserting a $1 coin and selecting a product which the machine has.
    * Listen To Your Experience.
2. (GREEN) Write the Simplest Thing that Works
* There are two things to remember when writing code that is necessary in order for a test to compile:
    * ll production code should be kept in a different directory tree from the tests. I suggest following the previously described pattern and putting it in src/main/java.
    * o nothing more than the minimum required for the test to compile. Create the necessary classes and  methods, but  do not  fit  them  out  with  any  business  logic.  Remember,  we  want  to  see  our  test fail now!

3. (BLUE) Refactor code end tests

In general, test doubles are used to replace DOCs, where this allows us to: 
    * gain full control over the environment in which the SUT is running, 
    * move beyond state testing and verify interactions between the SUT and its DOCs.

Types of test doubles
TEST-DOUBLE TYPE        ALSO KNOWN AS           DESCRIPTION
dummy object            dummy                   needs to exist, no real collaboration needed
test stub               stub                    used for passing some values to the SUT ("indirect inputs")
test spy                spy
mock object             mock                    used to verify if the SUT calls specific methods of the collaborator ("indirect outputs")

* Create test doubles for all DOCs except DTO, ValueObjects or JDK Lists, Sets Maps...

The  only  situations where  I  would  consider  using  a  real  collaborator  instead  of  a  test  double  are  the following: 
    * the collaborator is very, very simple, preferably without any logic (e.g. some sort of "container" class with only accessors and mutators methods),
    * the collaborator’s logic is so simple that it is clear how to set it in the desired state (and its logic will not be enhanced in the foreseeable future).
      Even then, I would be highly cautious, as changes are inevitable – no matter how very unlikely they may seem!
* With state testing, the SUT is a black box.
* With interaction testing, the SUT is a white box.
* You should select arguments that belong to these three groups:
    1. expected values (AKA happy path),
    2. boundary values,
    3. strange values (AKA validity or domain).

* Можно отключить тесты с помощью @Ignore или условий Assume.assumeXXX()
* В Mokito можно подменять методы с помощью when(mock).thenReturn(something) также можно подменять void методы doNothing().when() и doAnswer().when() or doThrow().when()

* It is time to list the advantages of matchers, which are numerous:
	* they enhance code readability, 
	* they help us to write assertions on the right abstraction level, 
	* they help to remove logic from tests (and encapsulate it within the matchers’ classes), 
	* they are highly reusable, which is very important if your domain objects are complex and are tested with multiple test scenarios, 
	* many matchers are available directly from matcher libraries - including specific matchers for work with collections (as shown in Section 6.12) or exceptions (see Section 6.4), 
	* writing custom matchers is a relatively simple task. 

The list below gives only a subset of all of the matchers available:
	* any() matches any object (or null), 
	* anyVararg() matches any number and values of arguments, 
	* isNull(), isNotNull() match null and not-null values respectively, 
	* anyBoolean(),  anyByte(),  anyChar(),  anyDouble(),  anyFloat(),  anyInt(),  anyLong(),  anyShort(), anyString() match these Java types (or null), 
	* isA(Class<T> clazz) matches any object of a given class, 
	* same(T value) matches an object which is the same (==) to a given object, 
	* anyCollection(),  anyList(),  anyMap(),  anySet()  matches  any  kind  of  instance  of  each sort  of collection (or null), 
	* refEq(T value, String... excludeFields) matches an object that is reflection-equal to the given value; allows us to specify excluded fields, not to be taken into account, 
	* eq(boolean value), eq(byte value), eq(char value), eq(double value), eq(float value), eq(int value), eq(long value), eq(short value), eq(T value) - matches values which are equal to given arguments.
	* startsWith(String prefix), endsWith(String suffix) match a string that starts/ends with the prefix/ suffix that is given, 
	* contains(String substring) matches a string that contains a given substring, 
	* matches(String regex) matches a string that matches a given regular expression.

Common JUnit Rules:
	* ErrorCollector: collect multiple errors in one test method 13 ,
	* ExpectedException: make flexible assertions about thrown exceptions,
	* ExternalResource: start and stop a server, for example,
	* TemporaryFolder: create fresh files, and delete after test,
	* TestName: remember the test name for use during the method,
	* TestWatcher: add logic at events during method execution,
	* Timeout: cause test to fail after a set time,
	* Verifier: fail test if object state ends up incorrect.

Для тестирования асинхронных методов можно:
    * Заснуть
    * Заснуть в цыкле
    * Воспользоваться библиотекой Awaitility framework
    * Сделать вызов синхронным

Тестирование CONCURRENCY с помощью Tempus-Fugit rules

* @ClassRule запускает правило в пределах всего теста, оно должно быть статическим
* @Rule Запускает првило каждый раз

* тестирование с Random значениями скорее недостаток

* тестовый метод должен тестить одно правило, это не означает что там должно содержаться только одни Assert
  (each test method should concentrate on a single feature of the SUT)

Private method testing
	* No one wants to promote private methods testing - but some of us believe that sometimes this is the only way.
	* Some developers demand that their code be tested and 100% object-oriented, while others believe that testing is enough and do not struggle to achieve clean design. 
	* When  writing new  code,  we  are  conveniently  positioned  to  write  it  so  that  it  is  fully  tested  via its public API. TDD might help to achieve this. 
	* When  working with  legacy  code  we  compromise  on  private  method  testing.  Since  the  code plays unfair, we also forget about fair-play.
* Если есть желание потестить приватный метод, это означает что скорее всего его нужно вынести в отдельный клас и тестировать через public API
* Есть два подхода для тестирования private methods
    1. Reflection (PowerMock)
    2. Relaxing Access Modifiers (package-private)
* Mockito может частично мокать объекты MySut sut = spy(new MySut());
                                                doReturn(LocalDate.ofEpochDay(15)).when(sut).getDate();
                                                
**Table 7.3. Comparison of new operator testing approaches** 

 |  | PowerMock |redesign | refactor & subclass | partial mocking
 |---|---------|--------|-------------------|---------------|
 |required SUT change | no change | API change, DI introduced (breaking clients) | refactoring -method extracted | refactoring -method extracted |
 |SUT’s design | no change | improved -business logic separated from collaborators creation | slightly worse than before (method extracted to facilitate testing) | slightly worse than before (method extracted to facilitate testing) |
 |test code | different than usual | simple | complicated, testing subclass of SUT | complicated, SUT is tested and also stubbed 
 |amount of work |minimal | might be significant | medium | medium (but less than Refactor & Subclass) 
 |SUT is a final class | not a problem | not a problem | can not use this technique | can not use this technique
 
 * ArgumentCaptor<?> of Mockito может помочь в перехвате аргументов (verify(sut).someMethod(captor.capture()))
 
 * Для создаия сложных объектов можно использовать билдеры и материнские объекты
 * Нужно больше сосредоточится на поведении SUT а не на его методах
 * Как показывает практика забудь о реализации и сосредоточся на требованиях, в этом тебе поможет TDD
 * Remember,  what  you  are  supposed  to  be  doing  is  testing  the  correctness  of  production  code.  Do  not make it any harder than necessary. 
   (don't include logic into your tests)
   
 **Соблюдать правило Диметры**
   
 **Соблюдать правило Клас не должен спрашивать у других данных, а должен сам делать**
 * Процедурно-ориентированый код трудно тестировать, поэтому нужно использовать TDD для того чтоб на выходе получить нормальный объектно ориентированый код
 * Do not copy other sloppy work! Do not become one of the blind led by the blind! An abyss awaits you if you do. (Wow, that has really got you scared, hasn’t it?) 
 * Every time a mock returns a mock, a fairy dies.
 * test only the minimally necessary set of features using each test method. 
 
 * We may sum things up here by saying that in order to avoid having to fix tests after code changes (which is pretty annoying, let’s face it), you should:
   + write good tests (i.e. loosely coupled to implementation), minimizing the number of failed tests, 
   + use test-first in all phases of the development process - both when working on new features and when introducing changes to the existing codebase.
    
 * we should write tests which verify the expected outcome of systems behaviour, and not the behaviour itself.
 
 * there  is  a  strong  relation  between  the  quality  of  your production code and test code
 * [Антипаттерны по тестированию ;)](https://habrahabr.ru/post/43761/)
 * make sure never to omit the RED phase of TDD, and always witness a failing test by actually seeing it fail. 
 
 ###Виды покрытия тестами
 * Line coverage - показывает вызывалась ли эта строка кода
 * Branch coverage - показывает выполнялось ли это условие(более сложно добится 100% покрытия)
 
 * Попробовать поработать с Cobertura