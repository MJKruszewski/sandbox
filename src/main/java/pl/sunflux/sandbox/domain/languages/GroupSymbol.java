package pl.sunflux.sandbox.domain.languages;

class GroupSymbol extends Group {
    GroupSymbol() {
        super(GroupTypes.symbol);
    }

    public void add(char a) {
        Random g = new Random(new Generator[0]);
        String[] symbols = LanguageGenerator.symbolMap.get(String.valueOf(a));
        if (symbols != null) {
            for (String symbol : symbols) {
                g.add(new Literal(symbol));
            }
        } else {
            g.add(new Literal(String.valueOf(a)));
        }
        super.add(g);
    }
}
