package com.intellij.idea.plugin.hybris.project.utils;

import com.google.common.collect.Lists;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.openapi.util.io.FileUtil.toSystemDependentName;

public final class FileUtils {
    private static final Logger LOG = Logger.getInstance(FileUtils.class);

    private FileUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Util class should never be instantiated.");
    }

    public static boolean isFileUnder(@NotNull final File child, @NotNull final File parent) {
        String parentPath;
        try {
            parentPath = toSystemDependentName(parent.getCanonicalPath()+"/");
        } catch (IOException e) {
            parentPath = toSystemDependentName(parent.getAbsolutePath()+"/");
        }
        String childPath;
        try {
            childPath = toSystemDependentName(child.getCanonicalPath()+"/");
        } catch (IOException e) {
            childPath = toSystemDependentName(child.getAbsolutePath()+"/");
        }

        return childPath.startsWith(parentPath);
    }

    @NotNull
    public static List<String> getPathToParentDirectoryFrom(
        @NotNull final File file,
        @NotNull final File parentDirectory
    ) throws IOException {
        if (!FileUtils.isFileUnder(file, parentDirectory)) {
            throw new IOException("File '" + file + "' is not under '" + parentDirectory + "'");
        }

        final List<String> path = new ArrayList<>();
        File currentDirectory = file.getParentFile();

        while (currentDirectory != null && !FileUtil.filesEqual(currentDirectory, parentDirectory)) {
            path.add(currentDirectory.getName());
            currentDirectory = currentDirectory.getParentFile();
        }

        final List<String> reversePath = Lists.reverse(path);
        LOG.info("Relative path for module dir " + file.getAbsolutePath() + " in " + parentDirectory.getAbsolutePath()
                 + " found as " + StringUtils.join(reversePath, '/'));
        return reversePath;
    }

    public static File toFile(final String path) {
        return toFile(path, false);
    }

    public static File toFile(final String path, boolean checkExists) {
        if (path == null) {
            return null;
        }
        File file = new File(path);
        if (checkExists && !file.exists()) {
            return null;
        }
        try {
            final String cannonPath = file.getCanonicalPath();
            file = new File(cannonPath);
        } catch (IOException e) {
           //ignore
        }
        return file;
    }

    public static File toFile(String parent, String child) {
        File file = new File(parent, child);
        try {
            final String cannonPath = file.getCanonicalPath();
            file = new File(cannonPath);
        } catch (IOException e) {
            //ignore
        }
        return file;
    }
}
