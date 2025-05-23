/*
 * This file is part of "SAP Commerce Developers Toolset" plugin for IntelliJ IDEA.
 * Copyright (C) 2014-2016 Alexander Bartash <AlexanderBartash@gmail.com>
 * Copyright (C) 2019-2025 EPAM Systems <hybrisideaplugin@epam.com> and contributors
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
package com.intellij.idea.plugin.hybris.project.descriptors

import com.intellij.idea.plugin.hybris.common.utils.HybrisIcons
import javax.swing.Icon

enum class ModuleDescriptorType(val icon: Icon = HybrisIcons.Y.LOGO_BLUE) {
    CONFIG(HybrisIcons.Extension.CONFIG),
    CUSTOM(HybrisIcons.Extension.CUSTOM),
    EXT(HybrisIcons.Extension.EXT),
    NONE,
    OOTB(HybrisIcons.Extension.OOTB),
    PLATFORM(HybrisIcons.Extension.PLATFORM),
    ECLIPSE,
    MAVEN,
    GRADLE,
    CCV2(HybrisIcons.Extension.CLOUD),
    ANGULAR(HybrisIcons.Module.ANGULAR),
}
