package util;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private static final String KO = "KO";
    private static final String OK = "OK";
    private final String path;
    private final Map<String, String[]> commandMap = new HashMap<>();

    public CommandProcessor(String path) {
        this.path = path;
        commandMap.put("list", listCommand(this.path));
        commandMap.put("show", showCommand(this.path));
        commandMap.put("delete", deleteCommand(this.path));
        commandMap.put("quit", quitCommand());
    }

    public CommandProcessor() {
        this.path = null;
        commandMap.put("quit", quitCommand());
    }

    public Map<String, String[]> getCommandMap() {
        return commandMap;
    }

    private String[] listCommand(String path) {
        try {
            Path dirPath = Paths.get(path);
            File dir = dirPath.toFile();

            if (!dir.exists() || !dir.isDirectory()) {
                return new String[]{KO};
            }

            StringBuilder output = new StringBuilder();
            File[] files = dir.listFiles();

            if (files == null) {
                return new String[]{KO};
            }

            for (File f : files) {
                String name = f.getName();
                long size = f.isDirectory() ? 0 : f.length() / 1024;

                output.append(name).append(" ").append(size).append(System.lineSeparator());
            }

            return new String[]{OK, output.toString()};

        } catch (Exception e) {
            return new String[]{KO};
        }
    }

    private String[] showCommand(String path) {
        try {
            Path p = Paths.get(path);

            if (!Files.exists(p) || Files.isDirectory(p)) return new String[]{KO};

            StringBuilder output = new StringBuilder();
            int lines = 0;

            for (String line : Files.readAllLines(p, Charset.defaultCharset())) {
                lines++;
                output.append(line).append(System.lineSeparator());
            }

            return new String[]{OK, lines + System.lineSeparator() + output};

        } catch (Exception e) {
            return new String[]{KO};
        }
    }

    private String[] deleteCommand(String path) {
        try {
            Files.delete(Paths.get(path));
            return new String[]{OK};
        } catch (Exception e) {
            return new String[]{KO};
        }
    }

    private String[] quitCommand() {
        return new String[]{OK};
    }



}

