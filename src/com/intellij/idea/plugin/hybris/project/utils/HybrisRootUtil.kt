/*
 * This file is part of "SAP Commerce Developers Toolset" plugin for Intellij IDEA.
 * Copyright (C) 2019-2023 EPAM Systems <hybrisideaplugin@epam.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.intellij.idea.plugin.hybris.project.utils

import com.intellij.idea.plugin.hybris.common.HybrisConstants
import com.intellij.idea.plugin.hybris.project.descriptors.ModuleDescriptorType
import com.intellij.idea.plugin.hybris.settings.components.ProjectSettingsComponent.Companion.getInstance
import com.intellij.lang.properties.psi.PropertiesFile
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import java.util.*

object HybrisRootUtil {
	fun findPlatformRootDirectory(project: Project): VirtualFile? {
		val settingsComponent = getInstance(project)
		val platformModule =
			Arrays.stream(ModuleManager.getInstance(project).modules)
					.filter { module: Module? -> settingsComponent.getModuleSettings(module!!).type == ModuleDescriptorType.PLATFORM }
					.findAny()
					.orElse(null)

		return if (platformModule == null)
			null
		else
			Arrays.stream(ModuleRootManager.getInstance(platformModule).contentRoots)
					.filter { vFile: VirtualFile? -> vFile!!.findChild(HybrisConstants.EXTENSIONS_XML) != null }
					.findAny()
					.orElse(null)
	}
}
