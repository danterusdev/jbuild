const std = @import("std");

pub fn main() anyerror!void {
    var args_in = std.process.args();
    _ = args_in.skip();

    var gpa = std.heap.GeneralPurposeAllocator(.{}){};
    const allocator = gpa.allocator();

    if (args_in.next()) |arg| {
        try std.os.chdir(arg);
    } else {
        std.debug.print("Must provide a directory!\n", .{});
        return;
    }

    const java_path = "/usr/bin/java";
    const javac_path = "/usr/bin/javac";

    {
        const args = [_:null] ?[*:0]const u8{javac_path, "Build.java", "-cp", "JBuild.jar"};
        const env = [_:null] ?[*:0]const u8{};

        try run_shell(javac_path, args[0..args.len], env[0..env.len]);
    }

    var arguments = std.ArrayList(?[*:0]const u8).init(allocator);
    try arguments.append(java_path);
    try arguments.append("-cp");
    try arguments.append("JBuild.jar:.");
    try arguments.append("Main");

    while (args_in.next()) |arg| {
        try arguments.append(arg);
    }

    {
        const args = try arguments.toOwnedSliceSentinel(null);
        const env = [_:null] ?[*:0] u8{};

        try run_shell(java_path, args, env[0..env.len]);
    }

    try std.fs.cwd().deleteFile("Build.class");
}

pub fn run_shell(path: [*:0]const u8, child_argv: [*:null]const ?[*:0]const u8, envp: [*:null]const ?[*:0]const u8) anyerror!void {
    const pid = try std.os.fork();
    if (pid == 0) {
        switch (std.os.execveZ(path, child_argv, envp)) {
            else => unreachable
        }
    }
    _ = std.os.waitpid(pid, 0);
}
