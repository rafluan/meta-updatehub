SUMMARY = "UpdateHub utilities for update package management and server"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d7d0c756730e640e1731676efa6782e9"

SRC_URI = "git://github.com/UpdateHub/uhu.git;protocol=https"
SRCREV = "3c3408cce8d5ec7f456d0544cd733c52dc0d1642"

PV = "1.0.0+${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

CLEANBROKEN = "1"

do_install_append_class-native() {
     sed -i -e '1s|^#!.*|#!/usr/bin/env python3|' ${D}${bindir}/uhu
     create_wrapper ${D}${bindir}/uhu
}

# FIXME: The native package is not adding the runtime dependencies for
# the sysroot populate. The issue is reported on YOCTO: #10113
do_populate_sysroot[rdeptask] = "do_populate_sysroot"

# FIXME: Runtime dependency of python3-prompt-toolkit which is not handled
# for native case.  The issue is also related to YOCTO: #10113
RDEPENDS_${PN} += " \
    python3-wcwidth \
    python3-six \
"

RDEPENDS_${PN} += " \
    updatehub-package-schema \
    python3-certifi \
    python3-chardet \
    python3-click \
    python3-humanize \
    python3-idna \
    python3-jsonschema \
    python3-pycrypto \
    python3-progress \
    python3-prompt-toolkit \
    python3-requests \
    python3-rfc3987 \
    python3-urllib3 \
"

BBCLASSEXTEND = "native nativesdk"
