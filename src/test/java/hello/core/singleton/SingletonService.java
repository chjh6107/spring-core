 package hello.core.singleton;

 public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    //싱글톤 조회 메소드
    public  static SingletonService getInstance(){
        return instance;
    }

    //외부에서 생성자를 호출하지 못하게 private 선언하여 생성자 생성
    private SingletonService() {

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
 }
