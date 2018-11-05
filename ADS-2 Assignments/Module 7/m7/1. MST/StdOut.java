/*************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *
 *  Writes data of various types to standard output.
 *
 *************************************************************************/

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 *  <i>Standard output</i>. This class provides methods for writing strings
 *  and numbers to standard output.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 */
public final class StdOut {
    /**
     * { UTF8 }.
     */
    private static final String UTF8 = "UTF-8";
    /**
     * { US_LOCALE }.
     */
    private static final Locale US_LOCALE = new Locale("en", "US");
    /**
     * { out }.
     */
    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, UTF8), true);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
    /**
     * Constructs the object.
     */
    private StdOut() { }
    /**
     * { close() }.
     * Complexity: O(1)
     */
    public static void close() {
        out.close();
    }
    /**
     * { println() }.
     * Complexity: O(1)
     */
    public static void println() {
        out.println();
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(Object x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(boolean x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(char x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(double x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(float x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(int x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(long x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(short x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(byte x) {
        out.println(x);
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print() {
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(Object x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(boolean x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(char x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(double x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(float x) {
        out.print(x);
        out.flush();
    }

    /**
      * Print an int to standard output and flush standard output.
      */
    public static void print(int x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(long x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(short x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print(byte x) {
        out.print(x);
        out.flush();
    }
    /**
     * { printf() }.
     * Complexity: O(1)
     */
    public static void printf(String format, Object... args) {
        out.printf(US_LOCALE, format, args);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }
}
