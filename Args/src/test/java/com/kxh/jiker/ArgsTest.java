package com.kxh.jiker;

import com.kxh.jiker.schema.ArgsSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArgsTest {
    ArgsSchema argsSchema;
    ArgsParserExecutor argsParserExecutor;

    @BeforeAll
    public void before_all() {
        this.argsSchema = ArgsParser.createSchema();
        this.argsParserExecutor = ArgsParser.createExecutor();
    }

    @Test
    public void shoud_show_default_value_of_arg_l() {
        String[] args = new String[]{"-l"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(false));
    }

    @Test
    public void shoud_show_setted_true_value_of_arg_l() {
        String[] args = new String[]{"-l", "true"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(true));
    }

    @Test
    public void shoud_show_setted_false_value_of_arg_l() {
        String[] args = new String[]{"-l", "false"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(false));
    }

    @Test
    public void should_show_exception_given_not_boolean_of_arg_l() {
        String[] args = new String[]{"-l", "1"};
        try {
            ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
            fail("Exception: Arg type of -l should be boolean, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Arg type of -l should be boolean"));
        }
    }

    @Test
    public void shoud_show_default_value_of_arg_p() {
        String[] args = new String[]{"-p"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("p"), is(8000));
    }

    @Test
    public void shoud_show_setted_port_value_of_arg_p() {
        String[] args = new String[]{"-p", "8080"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("p"), is(8080));
    }

    @Test
    public void should_show_exception_given_not_integer_of_arg_p() {
        String[] args = new String[]{"-p", "9d"};
        try {
            ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
            fail("Exception: Arg type of -p should be integer, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Arg type of -p should be integer"));
        }
    }

    @Test
    public void shoud_show_default_value_of_arg_d() {
        String[] args = new String[]{"-d"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("d"), is("/usr/logs"));
    }

    @Test
    public void shoud_show_setted_value_of_arg_d() {
        String[] args = new String[]{"-d", "/home/shgbit/logs"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("d"), is("/home/shgbit/logs"));
    }

    @Test
    public void shoud_show_default_value_of_arg_l_p_d() {
        String[] args = new String[]{"-l", "-p", "-d"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(false));
        assertThat(argsContext.getValue("p"), is(8000));
        assertThat(argsContext.getValue("d"), is("/usr/logs"));
    }

    @Test
    public void shoud_show_setted_value_of_arg_l_p_d() {
        String[] args = new String[]{"-l", "true", "-p", "7000", "-d", "/opt/logs"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(true));
        assertThat(argsContext.getValue("p"), is(7000));
        assertThat(argsContext.getValue("d"), is("/opt/logs"));
    }

    @Test
    public void shoud_show_default_and_setted_value_of_arg_l_p_d() {
        String[] args = new String[]{"-l", "-p", "7000", "-d"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        assertThat(argsContext.getValue("l"), is(false));
        assertThat(argsContext.getValue("p"), is(7000));
        assertThat(argsContext.getValue("d"), is("/usr/logs"));
    }

    @Test
    public void should_show_donot_recognize_exception_given_not_existed_arg() {
        String[] args = new String[]{"-a", "-p", "-d"};
        try {
            ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("Unable to identify parameter -a"));
        }
    }

    @Test
    public void should_show_arg_count_given_args() {
        String[] argWithoutValues = new String[]{"-l", "-p", "-d"};
        ArgsContext argsContext1 = ArgsParser.buildContext(argWithoutValues, this.argsParserExecutor, this.argsSchema);
        assertEquals(3, argsContext1.getArgsCount());

        String[] argWithValues = new String[]{"-l", "true", "-p", "8080", "-d", "/home/shgbit/logs"};
        ArgsContext argsContext2 = ArgsParser.buildContext(argWithValues, this.argsParserExecutor, this.argsSchema);
        assertEquals(3, argsContext2.getArgsCount());

        String[] argWithComposite = new String[]{"-l", "-p", "-d", "/home/shgbit/logs"};
        ArgsContext argsContext3 = ArgsParser.buildContext(argWithComposite, this.argsParserExecutor, this.argsSchema);
        assertEquals(3, argsContext3.getArgsCount());
    }

    @Test
    public void should_show_list_default_value_of_arg_g() {
        String[] args = new String[]{"-g", "-l"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        List<String> expected = new ArrayList<>();
        assertThat(argsContext.getValue("g"), is(expected));
    }

    @Test
    public void should_show_list_setted_value_of_arg_g() {
        String[] args = new String[]{"-g", "this,is,a,list"};
        ArgsContext argsContext = ArgsParser.buildContext(args, this.argsParserExecutor, this.argsSchema);
        List<String> expected = Arrays.asList("this", "is", "a", "list");
        assertThat(argsContext.getValue("g"), is(expected));
    }
}
