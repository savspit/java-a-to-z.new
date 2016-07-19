package shestakov;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.BooleanResult2;
import org.openjdk.jcstress.infra.results.LongResult2;

public class SharedObjectsCollision {

    @State
    public static class MyState {
        public final TestObject to = new TestObject();
    }

    @JCStressTest
    @Description("Test showing race condition collision")
    @Outcome(id="[true, true]", expect = Expect.ACCEPTABLE_INTERESTING, desc="right results, it`s ok")
    @Outcome(id="[false, true]", expect = Expect.ACCEPTABLE_INTERESTING, desc="shared objects condition occured")
    @Outcome(id="[true, false]", expect = Expect.ACCEPTABLE_INTERESTING, desc="shared objects condition occured")
    @Outcome(id="[false, false]", expect = Expect.ACCEPTABLE_INTERESTING, desc="shared objects condition occured")
    public static class StressTest1 {

        @Actor
        public void actor1(MyState myState, BooleanResult2 r) {
            int valueBefore = myState.to.value;
            myState.to.setValuesByZero();
            r.r1 = (valueBefore == 555 && myState.to.value != 555);
        }

        @Actor
        public void actor2(MyState myState, BooleanResult2 r) {
            int valueBefore = myState.to.value;
            myState.to.setValuesByZero();
            r.r2 = (valueBefore == 555 && myState.to.value != 555);
        }
    }

    public static class TestObject {
        public int value;

        public TestObject() {
            this.value = 555;
        }

        public void setValuesByZero() {
            this.value = 0;
        }

        public void setValuesByNine() {
            this.value = 999;
        }
    }
}
