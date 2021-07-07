package pl.sunflux.sandbox.domain.languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Generator {

    protected List<Generator> generators = new ArrayList<>();

    public Generator() {
    }

    public Generator(Generator[] pattern) {
        this.generators.addAll(Arrays.asList(pattern));
    }

    public Generator(String pattern, boolean collapse_triples) {
        Generator last;
        Stack<Group> stack = new Stack<>();
        Group top = new GroupSymbol();

        char[] patternStr = pattern.toCharArray();

        for (int i = 0; i < patternStr.length; i++) {
            char chr = patternStr[i];
            switch (chr) {
                case '<':
                    stack.add(top);
                    top = new GroupSymbol();
                    break;
                case '(':
                    stack.add(top);
                    top = new GroupLiteral();
                    break;
                case '>':
                case ')':
                    if (stack.size() == 0) {
                        throw new Error("Unbalanced brackets");
                    } else if (chr == '>' && top.type != GroupTypes.symbol) {
                        throw new Error("Unexpected \">\" in pattern");
                    } else if (chr == ')' && top.type != GroupTypes.literal) {
                        throw new Error("Unexpected \")\" in pattern");
                    }
                    last = top.produce();
                    top = stack.pop();
                    top.add(last);
                    break;
                case '|':
                    top.split();
                    break;
                case '!':
                    if (top.type == GroupTypes.symbol) {
                        top.wrap(WrappersEnum.capitalizer);
                    } else {
                        top.add(chr);
                    }
                    break;
                case '~':
                    if (top.type == GroupTypes.symbol) {
                        top.wrap(WrappersEnum.reverser);
                    } else {
                        top.add(chr);
                    }
                    break;
                default:
                    top.add(chr);
                    break;
            }
        }

        if (stack.size() != 0) {
            throw new Error("Missing closing brackets");
        }

        Generator g = top.produce();
        if (collapse_triples) {
            g = new Collapser(g);
        }
        this.add(g);
    }

    public double combinations() {
        double total = 1;

        for (Generator generator : this.generators) {
            total *= generator.combinations();
        }

        return total;
    }

    public int min() {
        int finale = 0;
        for (Generator generator : this.generators) {
            finale += generator.min();
        }
        return finale;
    }

    public int max() {
        int finale = 0;
        for (Generator generator : this.generators) {
            finale += generator.max();
        }
        return finale;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Generator generator : this.generators) {
            str.append(generator.toString());
        }
        return str.toString();
    }

    public void add(Generator g) {
        this.generators.add(g);
    }
}
