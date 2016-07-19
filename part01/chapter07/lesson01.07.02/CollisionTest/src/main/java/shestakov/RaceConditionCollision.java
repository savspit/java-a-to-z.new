package shestakov;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.BooleanResult2;
import org.openjdk.jcstress.infra.results.IntResult1;
import org.openjdk.jcstress.infra.results.LongResult2;

public class RaceConditionCollision {

    @State
    public static class MyState {
        public final TestObject to = new TestObject();
    }

    @JCStressTest
    @Description("Test showing race condition collision")
    @Outcome(id = "[false, true]", expect = Expect.ACCEPTABLE_INTERESTING, desc = "race condition occured")
    @Outcome(id = "[true, false]", expect = Expect.ACCEPTABLE_INTERESTING, desc = "right results, it`s ok")
    public static class StressTest1 {

        @Actor
        public void actor1(MyState myState) {
            myState.to.incrementByTwo();
        }

        @Actor
        public void actor2(MyState myState) {
            myState.to.incrementByTwo();
        }

        @Arbiter
        public void observe(MyState myState, BooleanResult2 res) {
            res.r1 = myState.to.counter == 5;
            res.r2 = myState.to.counter == 1;
        }

    }

    public static class TestObject {
        public int counter;

        public TestObject() {
            this.counter = 1;
        }

        public void incrementByTwo() {
            counter++;
            counter++;
        }
    }
}
