package com.beijunyi.parallelgit.filesystem.io;

import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collection;

import com.beijunyi.parallelgit.filesystem.AbstractGitFileSystemTest;
import org.eclipse.jgit.lib.FileMode;
import org.junit.Assert;
import org.junit.Test;

public class PosixGfsFileAttributesTest extends AbstractGitFileSystemTest {

  @Test
  public void getPermissionOfFile_shouldReturnNotNull() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Assert.assertNotNull(attributes.permissions());
  }

  @Test
  public void getPermissionOfFile_shouldContainOwnerRead() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Collection permissions = (Collection) attributes.permissions();
    Assert.assertTrue(permissions.contains(PosixFilePermission.OWNER_READ));
  }

  @Test
  public void getPermissionOfFile_shouldContainOwnerWrite() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Collection permissions = (Collection) attributes.permissions();
    Assert.assertTrue(permissions.contains(PosixFilePermission.OWNER_WRITE));
  }

  @Test
  public void getPermissionOfFile_shouldNotContainOwnerExecute() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Collection permissions = (Collection) attributes.permissions();
    Assert.assertFalse(permissions.contains(PosixFilePermission.OWNER_EXECUTE));
  }
  
  

  @Test
  public void getPermissionOfExecutableFile_shouldContainOwnerExecute() throws IOException {
    initRepository();
    writeFile("/file.txt", "some data".getBytes(), FileMode.EXECUTABLE_FILE);
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Collection permissions = (Collection) attributes.permissions();
    Assert.assertTrue(permissions.contains(PosixFilePermission.OWNER_EXECUTE));
  }

  @Test
  public void getOwnerOfFile_shouldReturnNull() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Assert.assertNull(attributes.owner());
  }

  @Test
  public void getGroupOfFile_shouldReturnNull() throws IOException {
    initRepository();
    writeFile("/file.txt");
    commitToMaster();
    initGitFileSystem();

    PosixFileAttributes attributes = provider.readAttributes(gfs.getPath("/file.txt"), PosixFileAttributes.class);
    Assert.assertNull(attributes.group());
  }

}
