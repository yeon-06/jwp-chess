package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class RegexTest {

    private static final String POSITION_INPUT_FORMAT_REGEX = "([abcdefgh][12345678])";
    private static final Pattern PATTERN = Pattern.compile(POSITION_INPUT_FORMAT_REGEX);

    @Test
    void a1_형식이면_참() {
        Matcher matcher = PATTERN.matcher("a1");

        boolean actual = matcher.matches();

        assertThat(actual).isTrue();
    }

    @Test
    void 알파벳_범위를_벗어나면_거짓() {
        Matcher matcher = PATTERN.matcher("i1");

        boolean actual = matcher.matches();

        assertThat(actual).isFalse();
    }

    @Test
    void 숫자_범위를_벗어나면_거짓() {
        Matcher matcher = PATTERN.matcher("a0");

        boolean actual = matcher.matches();

        assertThat(actual).isFalse();
    }

    @Test
    void 한글자면_거짓() {
        Matcher matcher = PATTERN.matcher("a");

        boolean actual = matcher.matches();

        assertThat(actual).isFalse();
    }

    @Test
    void 두글자를_넘어가면_거짓() {
        Matcher matcher = PATTERN.matcher("a12");

        boolean actual = matcher.matches();

        assertThat(actual).isFalse();
    }
}
