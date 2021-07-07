package pl.sunflux.sandbox.domain.languages;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Group {
    private Stack<WrappersEnum> wrappers = new Stack<>();
    private List<Generator> set = new ArrayList<>();

    public GroupTypes type;

    Group(GroupTypes type) {
        this.type = type;
    }

    public Generator produce() {
        switch (this.set.size()) {
            case 0:
                return new Literal("");
            case 1:
                return this.set.get(0);
            default:
                Generator[] generators = new Generator[this.set.size()];
                generators = this.set.toArray(generators);

                return new Random(generators);
        }
    }

    public void split() {
        if (this.set.size() == 0) {
            this.set.add(new Sequence(new Generator[0]));
        }
        this.set.add(new Sequence(new Generator[0]));
    }

    public void wrap(WrappersEnum type) {
        this.wrappers.add(type);
    }

    public void add(Generator a) {
        while (this.wrappers.size() > 0) {
            switch (this.wrappers.pop()) {
                case reverser:
                    a = new Reverser(a);
                    break;
                case capitalizer:
                    a = new Capitalizer(a);
                    break;
            }
        }
        if (this.set.size() == 0) {
            this.set.add(new Sequence(new Generator[0]));
        }

        this.set.get(this.set.size() - 1).add(a);
    }

    public void add(char a) {
        char chr = a;
        Random g = new Random(new Generator[]{});
        g.add(new Literal(String.valueOf(a)));
        this.add(g);
    }
}
