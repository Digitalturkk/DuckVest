package com.DuckVest.Services.Additional.DuckVestorServices;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DuckVestorTipsService {

    private static final List<String> tips = List.of(
            "Use it wisely. Remember: patience turns breadcrumbs into banquets.",
            "Even the smallest pond can grow a mighty duck. Start small — think big.",
            "Don’t just fly with the flock. Know where you're going.",
            "Diversify your nest. One egg per basket, unless you're feeling lucky.",
            "Buy the dip, but make sure you’re not swimming in shit.",
            "You don’t need to be the fastest swimmer. Just don’t stop paddling.",
            "Feed your portfolio like you’d feed your ducklings — with care and balance.",
            "HODL like a duck holds onto her eggs in stormy weather.",
            "Don’t chase the fish others already caught. Find your own stream.",
            "Warren Buffett once said: ‘Be fearful when others are greedy.’ Duckvestor says: ‘Quack when others cluck.’"
    );

    public static String getRandomAdvice() {
        int index = new Random().nextInt(tips.size());
        return tips.get(index);
    }
}
