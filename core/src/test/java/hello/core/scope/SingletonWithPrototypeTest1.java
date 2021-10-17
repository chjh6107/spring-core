package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

// import hello.core.scope.PrototypeTest.PrototypeBean;

public class SingletonWithPrototypeTest1 {
    
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
        ac.close();
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = 
            new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1=clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2=clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        ac.close();
    }

    //Provider 방법은 자바 표준이긴 하나, gradle에 라이브버리를 입력해주어야한다
    // implementation 'javax.inject:javax.inject:1'
    @Scope("singleton")
    static class ClientBean{
        // private final PrototypeBean prototypeBean; //생성시점에 주입되어버려 계속 같은 것을 사용ㄹ

        @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider; //DL방법
        private Provider<PrototypeBean> prototypeBeanProvider;//Provider 방법

        //생성자 한개라 Autowired 생략가능
        // @Autowired
        // public ClientBean(PrototypeBean prototypeBean) {
        //     this.prototypeBean=prototypeBean;
        // }

        public int logic(){
            // PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); //DL방법
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init "+this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
