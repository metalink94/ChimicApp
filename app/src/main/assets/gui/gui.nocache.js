function gui() {
    var O = 'bootstrap',
        P = 'begin',
        Q = 'gwt.codesvr.gui=',
        R = 'gwt.codesvr=',
        S = 'gui',
        T = 'startup',
        U = 'DUMMY',
        V = 0,
        W = 1,
        X = 'iframe',
        Y = '',
        Z = 'javascript:""',
        $ = 'position:absolute; width:0; height:0; border:none; left: -1000px;',
        _ = ' top: -1000px;',
        ab = 'javascript:var d=document.open();d.domain="',
        bb = '";void(0);',
        cb = '<script>document.domain = "',
        db = '";<\/script>',
        eb = 'CSS1Compat',
        fb = '<!doctype html>',
        gb = '<html><head><\/head><body>',
        hb = '<\/body><\/html>',
        ib = 'undefined',
        jb = 'DOMContentLoaded',
        kb = 50,
        lb = 'Chrome',
        mb = 'eval("',
        nb = '");',
        ob = 'script',
        pb = 'javascript',
        qb = 'moduleStartup',
        rb = 'moduleRequested',
        sb = 'Failed to load ',
        tb = 'head',
        ub = 'meta',
        vb = 'name',
        wb = 'gui::',
        xb = '::',
        yb = 'gwt:property',
        zb = 'content',
        Ab = '=',
        Bb = 'gwt:onPropertyErrorFn',
        Cb = 'Bad handler "',
        Db = '" for "gwt:onPropertyErrorFn"',
        Eb = 'gwt:onLoadErrorFn',
        Fb = '" for "gwt:onLoadErrorFn"',
        Gb = '#',
        Hb = '?',
        Ib = '/',
        Jb = 'img',
        Kb = 'clear.cache.gif',
        Lb = 'baseUrl',
        Mb = 'gui.nocache.js',
        Nb = 'base',
        Ob = '//',
        Pb = 'gecko.variant',
        Qb = 'user.agent',
        Rb = 'gecko1_8',
        Sb = 'none',
        Tb = 'trident',
        Ub = 'ie11',
        Vb = 'std',
        Wb = 2,
        Xb = 'onLoad',
        Yb = 'default',
        Zb = 'webkit',
        $b = 'safari',
        _b = 'msie',
        ac = 10,
        bc = 11,
        cc = 'ie10',
        dc = 9,
        ec = 'ie9',
        fc = 8,
        gc = 'ie8',
        hc = 'gecko',
        ic = 3,
        jc = 4,
        kc = 'selectingPermutation',
        lc = 'gui.devmode.js',
        mc = '66B86945A81C3729CF1AA8062F8EA475',
        nc = ':1',
        oc = 'foobar',
        pc = ':10',
        qc = ':11',
        rc = ':2',
        sc = ':3',
        tc = ':4',
        uc = ':5',
        vc = ':6',
        wc = ':7',
        xc = ':8',
        yc = ':9',
        zc = ':',
        Ac = '.cache.js',
        Bc = 'link',
        Cc = 'rel',
        Dc = 'stylesheet',
        Ec = 'href',
        Fc = 'loadExternalRefs',
        Gc = 'css/auto/clean.css',
        Hc = 'css/auto/gui.css',
        Ic = 'end',
        Jc = 'http:',
        Kc = 'file:',
        Lc = '_gwt_dummy_',
        Mc = '__gwtDevModeHook:gui',
        Nc = 'Ignoring non-whitelisted Dev Mode URL: ',
        Oc = ':moduleBase';
    var o = window;
    var p = document;
    r(O, P);

    function q() {
        var a = o.location.search;
        return a.indexOf(Q) != -1 || a.indexOf(R) != -1
    }

    function r(a, b) {
        if (o.__gwtStatsEvent) {
            o.__gwtStatsEvent({
                moduleName: S,
                sessionId: o.__gwtStatsSessionId,
                subSystem: T,
                evtGroup: a,
                millis: (new Date).getTime(),
                type: b
            })
        }
    }
    gui.__sendStats = r;
    gui.__moduleName = S;
    gui.__errFn = null;
    gui.__moduleBase = U;
    gui.__softPermutationId = V;
    gui.__computePropValue = null;
    gui.__getPropMap = null;
    gui.__installRunAsyncCode = function() {};
    gui.__gwtStartLoadingFragment = function() {
        return null
    };
    gui.__gwt_isKnownPropertyValue = function() {
        return false
    };
    gui.__gwt_getMetaProperty = function() {
        return null
    };
    var s = null;
    var t = o.__gwt_activeModules = o.__gwt_activeModules || {};
    t[S] = {
        moduleName: S
    };
    gui.__moduleStartupDone = function(e) {
        var f = t[S].bindings;
        t[S].bindings = function() {
            var a = f ? f() : {};
            var b = e[gui.__softPermutationId];
            for (var c = V; c < b.length; c++) {
                var d = b[c];
                a[d[V]] = d[W]
            }
            return a
        }
    };
    var u;

    function v() {
        w();
        return u
    }

    function w() {
        if (u) {
            return
        }
        var b = p.createElement(X);
        var c = Y;
        b.src = Z;
        b.id = S;
        b.style.cssText = $ + _;
        b.tabIndex = -1;
        p.body.appendChild(b);
        try {
            u = b.contentDocument;
            if (!u) {
                u = b.contentWindow.document
            }
        } catch (a) {
            b.src = ab + document.domain + bb;
            u = b.contentWindow.document;
            c = cb + document.domain + db
        }
        u.open();
        var d = document.compatMode == eb ? fb : Y;
        u.write(d + gb + c + hb);
        u.close()
    }

    function A(k) {
        function l(a) {
            function b() {
                if (typeof p.readyState == ib) {
                    return typeof p.body != ib && p.body != null
                }
                return /loaded|complete/.test(p.readyState)
            }
            var c = b();
            if (c) {
                a();
                return
            }

            function d() {
                if (!c) {
                    c = true;
                    a();
                    if (p.removeEventListener) {
                        p.removeEventListener(jb, d, false)
                    }
                    if (e) {
                        clearInterval(e)
                    }
                }
            }
            if (p.addEventListener) {
                p.addEventListener(jb, d, false)
            }
            var e = setInterval(function() {
                if (b()) {
                    d()
                }
            }, kb)
        }

        function m(c) {
            function d(a, b) {
                a.removeChild(b)
            }
            var e = v();
            var f = e.body;
            var g;
            if (navigator.userAgent.indexOf(lb) > -1 && window.JSON) {
                var h = e.createDocumentFragment();
                h.appendChild(e.createTextNode(mb));
                for (var i = V; i < c.length; i++) {
                    var j = window.JSON.stringify(c[i]);
                    h.appendChild(e.createTextNode(j.substring(W, j.length - W)))
                }
                h.appendChild(e.createTextNode(nb));
                g = e.createElement(ob);
                g.language = pb;
                g.appendChild(h);
                f.appendChild(g);
                d(f, g)
            } else {
                for (var i = V; i < c.length; i++) {
                    g = e.createElement(ob);
                    g.language = pb;
                    g.text = c[i];
                    f.appendChild(g);
                    d(f, g)
                }
            }
        }
        gui.onScriptDownloaded = function(a) {
            l(function() {
                m(a)
            })
        };
        r(qb, rb);
        var n = p.createElement(ob);
        n.src = k;
        if (gui.__errFn) {
            n.onerror = function() {
                gui.__errFn(S, new Error(sb + code))
            }
        }
        p.getElementsByTagName(tb)[V].appendChild(n)
    }
    gui.__startLoadingFragment = function(a) {
        return D(a)
    };
    gui.__installRunAsyncCode = function(a) {
        var b = v();
        var c = b.body;
        var d = b.createElement(ob);
        d.language = pb;
        d.text = a;
        c.appendChild(d);
        c.removeChild(d)
    };

    function B() {
        var c = {};
        var d;
        var e;
        var f = p.getElementsByTagName(ub);
        for (var g = V, h = f.length; g < h; ++g) {
            var i = f[g],
                j = i.getAttribute(vb),
                k;
            if (j) {
                j = j.replace(wb, Y);
                if (j.indexOf(xb) >= V) {
                    continue
                }
                if (j == yb) {
                    k = i.getAttribute(zb);
                    if (k) {
                        var l, m = k.indexOf(Ab);
                        if (m >= V) {
                            j = k.substring(V, m);
                            l = k.substring(m + W)
                        } else {
                            j = k;
                            l = Y
                        }
                        c[j] = l
                    }
                } else if (j == Bb) {
                    k = i.getAttribute(zb);
                    if (k) {
                        try {
                            d = eval(k)
                        } catch (a) {
                            alert(Cb + k + Db)
                        }
                    }
                } else if (j == Eb) {
                    k = i.getAttribute(zb);
                    if (k) {
                        try {
                            e = eval(k)
                        } catch (a) {
                            alert(Cb + k + Fb)
                        }
                    }
                }
            }
        }
        __gwt_getMetaProperty = function(a) {
            var b = c[a];
            return b == null ? null : b
        };
        s = d;
        gui.__errFn = e
    }

    function C() {
        function e(a) {
            var b = a.lastIndexOf(Gb);
            if (b == -1) {
                b = a.length
            }
            var c = a.indexOf(Hb);
            if (c == -1) {
                c = a.length
            }
            var d = a.lastIndexOf(Ib, Math.min(c, b));
            return d >= V ? a.substring(V, d + W) : Y
        }

        function f(a) {
            if (a.match(/^\w+:\/\//)) {} else {
                var b = p.createElement(Jb);
                b.src = a + Kb;
                a = e(b.src)
            }
            return a
        }

        function g() {
            var a = __gwt_getMetaProperty(Lb);
            if (a != null) {
                return a
            }
            return Y
        }

        function h() {
            var a = p.getElementsByTagName(ob);
            for (var b = V; b < a.length; ++b) {
                if (a[b].src.indexOf(Mb) != -1) {
                    return e(a[b].src)
                }
            }
            return Y
        }

        function i() {
            var a = p.getElementsByTagName(Nb);
            if (a.length > V) {
                return a[a.length - W].href
            }
            return Y
        }

        function j() {
            var a = p.location;
            return a.href == a.protocol + Ob + a.host + a.pathname + a.search + a.hash
        }
        var k = g();
        if (k == Y) {
            k = h()
        }
        if (k == Y) {
            k = i()
        }
        if (k == Y && j()) {
            k = e(p.location.href)
        }
        k = f(k);
        return k
    }

    function D(a) {
        if (a.match(/^\//)) {
            return a
        }
        if (a.match(/^[a-zA-Z]+:\/\//)) {
            return a
        }
        return gui.__moduleBase + a
    }

    function F() {
        var f = [];
        var g = V;

        function h(a, b) {
            var c = f;
            for (var d = V, e = a.length - W; d < e; ++d) {
                c = c[a[d]] || (c[a[d]] = [])
            }
            c[a[e]] = b
        }
        var i = [];
        var j = [];

        function k(a) {
            var b = j[a](),
                c = i[a];
            if (b in c) {
                return b
            }
            var d = [];
            for (var e in c) {
                d[c[e]] = e
            }
            if (s) {
                s(a, d, b)
            }
            throw null
        }
        j[Pb] = function() {
            try {
                if (!j.hasOwnProperty(Qb) || k(Qb) !== Rb) {
                    return Sb
                }
            } catch (a) {
                return Sb
            }
            if (navigator.userAgent.toLowerCase().indexOf(Tb) != -1) {
                return Ub
            }
            return Vb
        };
        i[Pb] = {
            'ie11': V,
            'none': W,
            'std': Wb
        };
        j[Xb] = function() {
            if (!o.marvin) {
                o.marvin = {
                    onLoadArray: [],
                    onLoad: function() {
                        if (!o.marvin.onLoadArray) {
                            return
                        }
                        for (var a = V; a < o.marvin.onLoadArray.length; a++) o.marvin.onLoadArray[a]();
                        delete o.marvin.onLoadArray
                    },
                    onReady: function(a) {
                        o.marvin.onLoadArray ? o.marvin.onLoadArray.push(a) : a()
                    }
                }
            }
            return Yb
        };
        i[Xb] = {
            'default': V,
            'foobar': W
        };
        j[Qb] = function() {
            var a = navigator.userAgent.toLowerCase();
            var b = p.documentMode;
            if (function() {
                    return a.indexOf(Zb) != -1
                }()) return $b;
            if (function() {
                    return a.indexOf(_b) != -1 && (b >= ac && b < bc)
                }()) return cc;
            if (function() {
                    return a.indexOf(_b) != -1 && (b >= dc && b < bc)
                }()) return ec;
            if (function() {
                    return a.indexOf(_b) != -1 && (b >= fc && b < bc)
                }()) return gc;
            if (function() {
                    return a.indexOf(hc) != -1 || b >= bc
                }()) return Rb;
            return $b
        };
        i[Qb] = {
            'gecko1_8': V,
            'ie10': W,
            'ie8': Wb,
            'ie9': ic,
            'safari': jc
        };
        __gwt_isKnownPropertyValue = function(a, b) {
            return b in i[a]
        };
        gui.__getPropMap = function() {
            var a = {};
            for (var b in i) {
                if (i.hasOwnProperty(b)) {
                    a[b] = k(b)
                }
            }
            return a
        };
        gui.__computePropValue = k;
        o.__gwt_activeModules[S].bindings = gui.__getPropMap;
        r(O, kc);
        if (q()) {
            return D(lc)
        }
        var l;
        try {
            h([Ub, Yb, Rb], mc);
            h([Vb, Yb, Rb], mc + nc);
            h([Sb, oc, ec], mc + pc);
            h([Sb, oc, $b], mc + qc);
            h([Sb, Yb, cc], mc + rc);
            h([Sb, Yb, gc], mc + sc);
            h([Sb, Yb, ec], mc + tc);
            h([Sb, Yb, $b], mc + uc);
            h([Ub, oc, Rb], mc + vc);
            h([Vb, oc, Rb], mc + wc);
            h([Sb, oc, cc], mc + xc);
            h([Sb, oc, gc], mc + yc);
            l = f[k(Pb)][k(Xb)][k(Qb)];
            var m = l.indexOf(zc);
            if (m != -1) {
                g = parseInt(l.substring(m + W), ac);
                l = l.substring(V, m)
            }
        } catch (a) {}
        gui.__softPermutationId = g;
        return D(l + Ac)
    }

    function G() {
        if (!o.__gwt_stylesLoaded) {
            o.__gwt_stylesLoaded = {}
        }

        function c(a) {
            if (!__gwt_stylesLoaded[a]) {
                var b = p.createElement(Bc);
                b.setAttribute(Cc, Dc);
                b.setAttribute(Ec, D(a));
                p.getElementsByTagName(tb)[V].appendChild(b);
                __gwt_stylesLoaded[a] = true
            }
        }
        r(Fc, P);
        c(Gc);
        c(Hc);
        r(Fc, Ic)
    }
    B();
    gui.__moduleBase = C();
    t[S].moduleBase = gui.__moduleBase;
    var H = F();
    if (o) {
        var I = !!(o.location.protocol == Jc || o.location.protocol == Kc);
        o.__gwt_activeModules[S].canRedirect = I;

        function J() {
            var b = Lc;
            try {
                o.sessionStorage.setItem(b, b);
                o.sessionStorage.removeItem(b);
                return true
            } catch (a) {
                return false
            }
        }
        if (I && J()) {
            var K = Mc;
            var L = o.sessionStorage[K];
            if (!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(L)) {
                if (L && (window.console && console.log)) {
                    console.log(Nc + L)
                }
                L = Y
            }
            if (L && !o[K]) {
                o[K] = true;
                o[K + Oc] = C();
                var M = p.createElement(ob);
                M.src = L;
                var N = p.getElementsByTagName(tb)[V];
                N.insertBefore(M, N.firstElementChild || N.children[V]);
                return false
            }
        }
    }
    G();
    r(O, Ic);
    A(H);
    return true
}
gui.succeeded = gui();