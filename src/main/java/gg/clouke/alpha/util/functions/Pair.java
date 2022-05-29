package gg.clouke.alpha.util.functions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Pair<F, S> {

    private F first;
    private S second;

}

