import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Getter
public class Foo {
    private List<Bar> bars;

    public List<Bar> getBars() {
        return bars;
    }
}
