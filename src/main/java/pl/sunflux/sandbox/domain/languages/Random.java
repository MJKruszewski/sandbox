package pl.sunflux.sandbox.domain.languages;

class Random extends Generator {
    Random(Generator[] generators) {
        super(generators);
    }

    public double combinations() {
        double total = 0;
        for (Generator generator : this.generators) {
            total += generator.combinations();
        }
        return total > 1 ? total : 1;
    }

    public int min() {
        int finale = -1;
        for (Generator generator : this.generators) {
            int curr = generator.min();
            if (curr < finale) {
                finale = curr;
            }
        }
        return finale;
    }

    public int max() {
        int finale = 0;
        for (Generator generator : this.generators) {
            int curr = generator.max();
            if (curr > finale) {
                finale = curr;
            }
        }
        return finale;
    }

    public String toString() {
        if (this.generators.isEmpty()) {
            return "";
        }
        double rnd = Math.floor(Math.random() * this.generators.size());
        return this.generators.get((int) rnd).toString();
    }
}
