package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.reflections.Reflections;

public abstract class MMM_ManagerBase {

	/**
	 * 追加処理の本体
	 */
	public abstract boolean append(Class pclass);

	public abstract String getPreFix();

	public void load(Class<?> cls) {
		// ロード
		{
			Set<? extends Class<?>> classes = new Reflections("").getSubTypesOf(cls);
			for (Class<?> lclass : classes) {
				if (Modifier.isAbstract(lclass.getModifiers())) {
					continue;
				}
				append(lclass);
			}
		}

		// 開発用
		Package lpackage = mod_MMM_MMMLib.class.getPackage();
		String ls = "";
		if (lpackage != null) {
			ls = mod_MMM_MMMLib.class.getPackage().getName().replace('.', File.separatorChar);
		}
		File lf1 = new File(MMM_FileManager.minecraftJar, ls);

		if (lf1.isDirectory()) {
			// ディレクトリの解析
			decodeDirectory(lf1);
		} else {
			// Zipの解析
			decodeZip(lf1);
		}

		// mods
		for (Entry<String, List<File>> le : MMM_FileManager.fileList.entrySet()) {
			for (File lf : le.getValue()) {
				if (lf.isDirectory()) {
					// ディレクトリの解析
					decodeDirectory(lf);
				} else {
					// Zipの解析
					decodeZip(lf);
				}
			}
		}
	}

	private void decodeDirectory(File pfile) {
		// ディレクトリ内のクラスを検索
		for (File lf : pfile.listFiles()) {
			if (lf.isFile()) {
				String lname = lf.getName();
				if (lname.indexOf(getPreFix()) > 0 && lname.endsWith(".class")) {
					// 対象クラスファイルなのでロード
					loadClass(lf.getName());
				}
			}
		}
	}

	private void decodeZip(File pfile) {
		// zipファイルを解析
		try {
			FileInputStream fileinputstream = new FileInputStream(pfile);
			ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
			ZipEntry zipentry;

			do {
				zipentry = zipinputstream.getNextEntry();
				if (zipentry == null) {
					break;
				}
				if (!zipentry.isDirectory()) {
					String lname = zipentry.getName();
					if (lname.indexOf(getPreFix()) > 0 && lname.endsWith(".class")) {
						loadClass(zipentry.getName());
					}
				}
			} while (true);

			zipinputstream.close();
			fileinputstream.close();
		} catch (Exception exception) {
			mod_MMM_MMMLib.Debug("add%sZip-Exception.", getPreFix());
		}

	}

	private void loadClass(String pname) {
		// 対象ファイルをクラスとしてロード
		try {
			ClassLoader lclassLoader = mod_MMM_MMMLib.class.getClassLoader();
			Package lpackage = mod_MMM_MMMLib.class.getPackage();
			String lclassname = pname.replace(".class", "");
			Class lclass;
			if (lpackage != null) {
				lclassname = (new StringBuilder(String.valueOf(lpackage.getName()))).append(".").append(lclassname)
						.toString();
				lclass = lclassLoader.loadClass(lclassname);
			} else {
				lclass = Class.forName(lclassname);
			}
			if (Modifier.isAbstract(lclass.getModifiers())) {
				return;
			}
			if (append(lclass)) {
				mod_MMM_MMMLib.Debug("get%sClass-done: %s", getPreFix(), lclassname);
			} else {
				mod_MMM_MMMLib.Debug("get%sClass-fail: %s", getPreFix(), lclassname);
			}
			/*
			if (!(MMM_ModelStabilizerBase.class).isAssignableFrom(lclass) || Modifier.isAbstract(lclass.getModifiers())) {
				mod_MMM_MMMLib.Debug(String.format(String.format("get%sClass-fail: %s", pprefix, lclassname)));
			    return;
			}

			MMM_ModelStabilizerBase lms = (MMM_ModelStabilizerBase)lclass.newInstance();
			pmap.put(lms.getName(), lms);
			mod_MMM_MMMLib.Debug(String.format("get%sClass-done: %s[%s]", pprefix, lclassname, lms.getName()));
			*/
		} catch (Exception exception) {
			mod_MMM_MMMLib.Debug("get%sClass-Exception.", getPreFix());
		} catch (Error error) {
			mod_MMM_MMMLib.Debug("get%sClass-Error: %s", getPreFix(), pname);
			error.printStackTrace();
		}

	}

}
